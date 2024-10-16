package com.matheus.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilitarios {

    // Método para aplicar a máscara de CPF no campo
    public static void aplicarMascaraCPF(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Remove qualquer caractere não numérico
            String digits = newValue.replaceAll("[^\\d]", "");

            // Limita a entrada a 11 dígitos
            if (digits.length() > 11) {
                digits = digits.substring(0, 11);
            }

            // Adiciona a máscara conforme os números são digitados
            StringBuilder maskedCPF = new StringBuilder();
            int length = digits.length();

            for (int i = 0; i < length; i++) {
                maskedCPF.append(digits.charAt(i));
                if ((i == 2 || i == 5) && i < length - 1) {
                    maskedCPF.append('.');
                } else if (i == 8 && i < length - 1) {
                    maskedCPF.append('-');
                }
            }

            // Define o texto formatado no campo
            textField.setText(maskedCPF.toString());

            // Coloca o cursor no final
            textField.positionCaret(maskedCPF.length());
        });
    }

    // Método para aplicar a máscara de Data de Nascimento no campo
    public static void aplicarMascaraDataNascimento(TextField campoDataNascimento) {
        campoDataNascimento.textProperty().addListener((observable, oldValue, newValue) -> {
            // Remove qualquer caractere que não seja número
            String valorDigitado = newValue.replaceAll("[^\\d]", "");

            // Aplica a máscara dd/mm/yyyy
            if (valorDigitado.length() > 8) {
                valorDigitado = valorDigitado.substring(0, 8);
            }

            StringBuilder valorMascarado = new StringBuilder(valorDigitado);

            if (valorDigitado.length() >= 3) {
                valorMascarado.insert(2, "/");
            }
            if (valorDigitado.length() >= 5) {
                valorMascarado.insert(5, "/");
            }

            // Atualiza o campo com a máscara aplicada
            campoDataNascimento.setText(valorMascarado.toString());

            // Coloca o cursor no final
            campoDataNascimento.positionCaret(valorMascarado.length());
        });
    }

    // Método para aplicar a máscara de Telefone no campo
    public static void aplicarMascaraTelefone(TextField campoTelefone) {
        campoTelefone.textProperty().addListener((observable, oldValue, newValue) -> {
            // Remove qualquer caractere que não seja número
            String valorDigitado = newValue.replaceAll("[^\\d]", "");

            // Limita o tamanho do texto a 11 caracteres (DDD + 9 dígitos)
            if (valorDigitado.length() > 11) {
                valorDigitado = valorDigitado.substring(0, 11);
            }

            StringBuilder valorMascarado = new StringBuilder(valorDigitado);

            if (valorDigitado.length() >= 1) {
                valorMascarado.insert(0, "(");  // Abre parênteses no começo
            }
            if (valorDigitado.length() >= 3) {
                valorMascarado.insert(3, ") "); // Fecha parênteses e adiciona o espaço diretamente após
            }
            if (valorDigitado.length() >= 8) {
                valorMascarado.insert(10, "-"); // Adiciona o traço depois do quinto dígito
            }

            // Atualiza o campo com a máscara aplicada
            campoTelefone.setText(valorMascarado.toString());

            // Coloca o cursor no final
            campoTelefone.positionCaret(valorMascarado.length());
        });
    }

    // Formatação da data (YYYY-MM-DD -> DD/MM/YYYY)
    public static String formatarData(String data) {
        Pattern pattern = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})");
        Matcher matcher = pattern.matcher(data);

        if (matcher.matches()) {
            return matcher.group(3) + "/" + matcher.group(2) + "/" + matcher.group(1);
        }
        return data;
    }

    // Formatação do CPF (XXXXXXXXXXX -> XXX.XXX.XXX-XX)
    public static String formatarCPF(String cpf) {
        Pattern pattern = Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})");
        Matcher matcher = pattern.matcher(cpf);

        if (matcher.matches()) {
            return matcher.group(1) + "." + matcher.group(2) + "." + matcher.group(3) + "-" + matcher.group(4);
        }
        return cpf;
    }

    // Formatação do Telefone (XXXXXXXXXXX -> (XX) XXXXX-XXXX)
    public static String formatarTelefone(String telefone) {
        Pattern pattern = Pattern.compile("(\\d{2})(\\d{5})(\\d{4})");
        Matcher matcher = pattern.matcher(telefone);

        if (matcher.matches()) {
            return "(" + matcher.group(1) + ") " + matcher.group(2) + "-" + matcher.group(3);
        }
        return telefone;
    }

    // Método responsável por converter uma data para o formato Americano
    public static String converterDataParaFormatoAmericano(String data) {
        String[] partesData = data.replace("/", "-").split("-");
        return partesData[2] + "-" + partesData[1] + "-" + partesData[0];
    }

    // Método para validar se campos estão preenchidos
    public static boolean validarCampos(TextField... fields) {
        boolean allValid = true;

        for (TextField field : fields) {
            if (field.getText().trim().isEmpty()) {
                field.setStyle("-fx-border-color: red;"); // Destaca o campo com borda vermelha
                allValid = false;
            } else {
                field.setStyle(""); // Remove o destaque se preenchido
            }
        }

        if (!allValid) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos obrigatórios");
            alert.setHeaderText("Preencha todos os campos.");
            alert.setContentText("Por favor, preencha todos os campos obrigatórios.");
            alert.showAndWait();
        }

        return allValid;
    }

    // Método exibirAlerta atualizado
    public static Optional<ButtonType> exibirAlerta(Alert.AlertType tipoAlerta, String titulo, String mensagem, ButtonType... botoes) {
        Alert alert = new Alert(tipoAlerta);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.getButtonTypes().setAll(botoes);
        return alert.showAndWait();
    }

    // Método responsável por limpar os campos do Formulário
    public static void limparCampos(TextField... campos) {
        for (TextField campo : campos) {
            campo.clear();
        }
    }

    // Método responsável por definir a quantidade maxima de Caracteres em cada Campo
    public static void limitarCaracteres(TextField textField, int maxLength) {
        // Define um TextFormatter com uma função de filtragem
        textField.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().length() <= maxLength ? change : null));
    }


}