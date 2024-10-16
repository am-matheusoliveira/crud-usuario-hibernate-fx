// Importação de Pacotes
package com.matheus.ui;

// Importação de Classes

import com.matheus.modelo.Usuario;
import com.matheus.dao.UsuarioDAO;
import com.matheus.util.Utilitarios;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import javafx.event.EventHandler;

// Declaração da Classe
public class UIConfigUser {

    // Declarações de variáveis
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private TableView<Usuario> tableView;
    private Usuario usuarioSelecionado;

    // Inicia a aplicação
    public void start(Stage stage) {
        // Criando a TableView
        criarTableView();

        // Criação do Layout principal, incluindo a função para criar os Botões
        HBox buttonsBox = new HBox(
                10,
                criarBotoes("Buscar"),
                criarBotoes("Novo"),
                criarBotoes("Atualizar"),
                criarBotoes("Excluir")
        );
        VBox vbox = new VBox(10, buttonsBox, tableView);

        // Permite que o TableView cresça
        VBox.setVgrow(tableView, Priority.ALWAYS);
        vbox.setPadding(new Insets(10));

        // Configurando a cena e exibindo a janela
        Scene scene = new Scene(vbox, 1100, 670);
        stage.setScene(scene);
        stage.setTitle("Sistema de Usuários");
        stage.show();
    }

    // Método responsável por buscar usuários e atualizar o TableView
    private void buscarUsuarios() {
        List<Usuario> usuarios = usuarioDAO.listarUsuarios();
        ObservableList<Usuario> observableUsuarios = FXCollections.observableArrayList(usuarios);
        tableView.setItems(observableUsuarios);
    }

    // Método responsável por mostrar o Formulário(Cadastrar/Atualizar) para o usuário
    private void criarFormulario(String acaoTipo) {
        Stage formularioStage = new Stage();
        formularioStage.setTitle(acaoTipo.equals("Novo") ? "Novo Usuário" : "Atualizar Usuário");

        // Definir largura e altura fixas
        formularioStage.setMinWidth(750);
        formularioStage.setMinHeight(500);
        formularioStage.setResizable(false);

        // Definindo o Stage como modal
        formularioStage.initModality(Modality.APPLICATION_MODAL);

        // Criando os campos do formulário e atribuindo formatações
        TextField nomeField = new TextField();
        Utilitarios.limitarCaracteres(nomeField, 100);

        TextField emailField = new TextField();
        Utilitarios.limitarCaracteres(emailField, 100);

        PasswordField senhaField = new PasswordField();
        Utilitarios.limitarCaracteres(senhaField, 255);

        TextField dataNascimentoField = new TextField();
        TextField cpfField = new TextField();
        TextField telefoneField = new TextField();

        TextField enderecoField = new TextField();
        Utilitarios.limitarCaracteres(enderecoField, 255);
        enderecoField.setPrefWidth(400);

        // Verificando se ação e do Tipo Atualizar
        if (acaoTipo.equals("Atualizar")) {
            usuarioSelecionado = tableView.getSelectionModel().getSelectedItem();
            if (usuarioSelecionado == null) {

                Utilitarios.exibirAlerta(
                        Alert.AlertType.WARNING,
                        "Nenhum Usuário Selecionado",
                        "Por favor, selecione um usuário na tabela para atualizar.",
                        new ButtonType("OK", ButtonBar.ButtonData.OK_DONE)
                );

                // Finaliza a função com um return vazio
                return;
            }

            // Atribuindo valores aos campos para esse caso de Atualização
            nomeField.setText(usuarioSelecionado.getNome());
            emailField.setText(usuarioSelecionado.getEmail());
            senhaField.setText("");  // Senha deve ser inserida novamente
            dataNascimentoField.setText(Utilitarios.formatarData(usuarioSelecionado.getDataNascimento()));
            cpfField.setText(Utilitarios.formatarCPF(usuarioSelecionado.getCpf()));
            telefoneField.setText(Utilitarios.formatarTelefone(usuarioSelecionado.getTelefone()));
            enderecoField.setText(usuarioSelecionado.getEndereco());
        }

        // Atribuindo mascaras aos campos
        Utilitarios.aplicarMascaraCPF(cpfField);
        Utilitarios.aplicarMascaraDataNascimento(dataNascimentoField);
        Utilitarios.aplicarMascaraTelefone(telefoneField);

        // Botão de ação (Cadastrar ou Atualizar)
        Button acaoButton = new Button(acaoTipo.equals("Novo") ? "Cadastrar" : "Atualizar");
        acaoButton.setOnAction(e -> {
            if (Utilitarios.validarCampos(nomeField, emailField, senhaField, dataNascimentoField, cpfField, telefoneField, enderecoField)) {
                String cpfSemMascara = cpfField.getText().replaceAll("[^\\d]", "");
                String telefoneSemMascara = telefoneField.getText().replaceAll("[^\\d]", "");
                String dataNascimentoSemMascara = Utilitarios.converterDataParaFormatoAmericano(dataNascimentoField.getText());

                if (acaoTipo.equals("Novo")) {
                    Usuario novoUsuario = new Usuario();
                    novoUsuario.setNome(nomeField.getText());
                    novoUsuario.setEmail(emailField.getText());
                    novoUsuario.setSenha(senhaField.getText());
                    novoUsuario.setDataNascimento(dataNascimentoSemMascara);
                    novoUsuario.setCpf(cpfSemMascara);
                    novoUsuario.setTelefone(telefoneSemMascara);
                    novoUsuario.setEndereco(enderecoField.getText());

                    usuarioDAO.salvar(novoUsuario);

                    Utilitarios.limparCampos(nomeField, emailField, senhaField, dataNascimentoField, cpfField, telefoneField, enderecoField);
                } else {
                    usuarioSelecionado.setNome(nomeField.getText());
                    usuarioSelecionado.setEmail(emailField.getText());
                    usuarioSelecionado.setSenha(senhaField.getText());
                    usuarioSelecionado.setDataNascimento(dataNascimentoSemMascara);
                    usuarioSelecionado.setCpf(cpfSemMascara);
                    usuarioSelecionado.setTelefone(telefoneSemMascara);
                    usuarioSelecionado.setEndereco(enderecoField.getText());
                    usuarioDAO.atualizar(usuarioSelecionado);

                    // Atualizando a tabela de Dados TableView apos finalizada a ação
                    tableView.refresh();
                }

                // Re-buscando os dados
                buscarUsuarios();

                // Fechando o Modal apos finalizada a ação
                formularioStage.close();
            }
        });

        // Espaço adicional abaixo da linha divisória
        Region spacerLinha = new Region();
        spacerLinha.setMinHeight(10);  // Define 10px de espaçamento fixo abaixo da linha

        // Linha de divisão
        Separator linhaDivisoria = new Separator();
        linhaDivisoria.setPrefWidth(500);

        // Alinhando o botão no canto inferior direito
        HBox buttonBox = new HBox();
        buttonBox.setPadding(new Insets(10, 10, 10, 10));
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.getChildren().add(acaoButton);

        // Layout do Formulário
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        // Configurações da primeira coluna (rótulos)
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setMinWidth(100); // Largura mínima para os rótulos
        col1.setMaxWidth(150); // Largura máxima para os rótulos

        // Configurações da segunda coluna (campos de texto)
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS); // Permite que os campos cresçam horizontalmente

        // Adição das colunas ao Componente GridPane
        grid.getColumnConstraints().addAll(col1, col2);

        // Configuração dos Botões e Labels
        grid.add(new Label("Nome:"), 0, 0);
        grid.add(nomeField, 1, 0);
        grid.add(new Label("Email:"), 0, 1);
        grid.add(emailField, 1, 1);
        grid.add(new Label("Senha:"), 0, 2);
        grid.add(senhaField, 1, 2);
        grid.add(new Label("Data Nascimento:"), 0, 3);
        grid.add(dataNascimentoField, 1, 3);
        grid.add(new Label("CPF:"), 0, 4);
        grid.add(cpfField, 1, 4);
        grid.add(new Label("Telefone:"), 0, 5);
        grid.add(telefoneField, 1, 5);
        grid.add(new Label("Endereço:"), 0, 6);
        grid.add(enderecoField, 1, 6);

        // Layout geral do formulário
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        // Layout geral do formulário
        VBox vbox = new VBox(10, grid, spacer, spacerLinha, linhaDivisoria, buttonBox);
        vbox.setPadding(new Insets(10));

        // Cena do Formulário (agora usando VBox como root)
        Scene scene = new Scene(vbox, 600, 500);
        formularioStage.setScene(scene);
        formularioStage.show();
    }

    // Método responsável por excluir um usuário
    private void excluirUsuario() {
        // Pegando o usuário selecionado no TableView
        usuarioSelecionado = tableView.getSelectionModel().getSelectedItem();

        if (usuarioSelecionado != null) {

            // Criando botões personalizados
            ButtonType yesButton = new ButtonType("Sim", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);

            // Exibir confirmação de exclusão com os botões específicos
            Optional<ButtonType> resultado = Utilitarios.exibirAlerta(
                    Alert.AlertType.CONFIRMATION,
                    "Confirmação de Exclusão",
                    "Tem certeza que deseja excluir o usuário " + usuarioSelecionado.getNome() + "?",
                    yesButton,
                    cancelButton
            );

            // Verifica se o usuário clicou em "Sim"
            if (resultado.isPresent() && resultado.get() == yesButton) {
                // Excluir o usuário do banco de dados
                usuarioDAO.deletar(usuarioSelecionado.getId());

                // Atualizar a lista de usuários
                buscarUsuarios();
            }

        } else {
            // Caso nenhum usuário esteja selecionado, exibe um alerta
            Utilitarios.exibirAlerta(
                    Alert.AlertType.WARNING,
                    "Nenhum Usuário Selecionado",
                    "Por favor, selecione um usuário na tabela para excluir.",
                    new ButtonType("OK", ButtonBar.ButtonData.OK_DONE)
            );
        }
    }

    // Método responsável por chamar e atribuir a função que cria os botões
    public Button criarBotoes(String btnNome) {
        switch (btnNome) {
            case "Buscar":
                return criarBotao("Buscar Usuários", e -> buscarUsuarios());
            case "Novo":
                return criarBotao("Novo Usuário", e -> criarFormulario("Novo"));
            case "Atualizar":
                return criarBotao("Atualizar Usuário", e -> criarFormulario("Atualizar"));
            case "Excluir":
                return criarBotao("Excluir Usuário", e -> excluirUsuario());
            default:
                return new Button("Ação Desconhecida");
        }
    }

    // Método responsável por criar os botões com um texto personalizado e atribuir um evento
    private Button criarBotao(String nome, EventHandler<ActionEvent> evento) {
        // Criando um novo Botão
        Button button = new Button(nome);

        // Aplicando evento ao Botão
        button.setOnAction(evento);

        // Retorna o botão configurado
        return button;
    }

    // Método responsável por criar o TableView e definir suas colunas
    public void criarTableView() {
        // Criandno um novo TableView
        tableView = new TableView<>();

        // Adicionando todas as colunas ao TableView
        tableView.getColumns().addAll(
                criarColuna(50.0, "ID", "id"),
                criarColuna(170.0, "Nome", "nome"),
                criarColuna(170.0, "Email", "email"),
                criarColunaFormatada(100.0, "DT Nascimento", Usuario::getDataNascimento, Utilitarios::formatarData),
                criarColunaFormatada(100.0, "CPF", Usuario::getCpf, Utilitarios::formatarCPF),
                criarColunaFormatada(100.0, "Telefone", Usuario::getTelefone, Utilitarios::formatarTelefone),
                criarColuna(220.0, "Endereço", "endereco"),
                criarColunaFormatada(150.0, "DT Cadastro", Usuario::getDataCadastro, dataCadastro ->
                        dataCadastro.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
                )
        );
    }

    // Método responsável por criar colunas
    private <T> TableColumn<Usuario, T> criarColuna(Double columnWidth, String titulo, String propriedade) {
        // Criando uma nova coluna com um Titulo passado como parâmetro
        TableColumn<Usuario, T> coluna = new TableColumn<>(titulo);
        coluna.setPrefWidth(columnWidth);

        // Atribuindo valor na coluna
        coluna.setCellValueFactory(new PropertyValueFactory<>(propriedade));

        // Retorna a coluna configurada
        return coluna;
    }

    // Método responsável por criar colunas e aplicar formatações nos seus valores
    private <T> TableColumn<Usuario, String> criarColunaFormatada(Double columnWidth, String titulo, Function<Usuario, T> propriedadeFunc, Function<T, String> formatadorFunc) {
        // Criando uma nova coluna com um Titulo passado como parâmetro
        TableColumn<Usuario, String> coluna = new TableColumn<>(titulo);
        coluna.setPrefWidth(columnWidth);

        // Atribuindo valor na coluna e apliicando formatação
        coluna.setCellValueFactory(cellData -> {
            // Pegando o valor do campo cellData
            T valor = propriedadeFunc.apply(cellData.getValue());

            // Aplicando a formatação
            String valorFormatado = (valor != null) ? formatadorFunc.apply(valor) : "";

            // Retornando o valor com a formatação aplicada
            return new SimpleStringProperty(valorFormatado);
        });

        // Retorna a coluna configurada
        return coluna;
    }
}