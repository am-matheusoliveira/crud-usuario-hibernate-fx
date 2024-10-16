DROP DATABASE IF EXISTS database_usuario;

CREATE DATABASE database_usuario
DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_general_ci;

USE database_usuario;

CREATE TABLE usuario(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    nome            VARCHAR(100) NOT NULL,
    email           VARCHAR(100) NOT NULL UNIQUE,
    senha           VARCHAR(255) NOT NULL,
    data_nascimento DATE NOT NULL,
    cpf             VARCHAR(11) UNIQUE,
    telefone        VARCHAR(11) NOT NULL,
    endereco        VARCHAR(255) NOT NULL,
    data_cadastro   DATETIME DEFAULT NOW()
)ENGINE=InnoDB CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO usuario (nome, email, senha, data_nascimento, cpf, telefone, endereco, data_cadastro) VALUES ('Breno Barros', 'da-cruzluigi@uol.com.br', '+e#6TZ*u^z4r', '1965-12-09', '34290567134', '55719134016', 'Via da Mata, Conjunto Califórnia Ii, 03212-628 Cardoso Paulista / BA', '2024-02-19 00:06:33');
INSERT INTO usuario (nome, email, senha, data_nascimento, cpf, telefone, endereco, data_cadastro) VALUES ('Luana da Cruz', 'araujoenzo-gabriel@yahoo.com.br', '&(5XPgbu(L*n', '1977-09-26', '46839527000', '55719192211', 'Jardim de Ribeiro, 3, Santa Tereza, 63595-224 Azevedo do Oeste / PR', '2024-05-06 08:14:20');
INSERT INTO usuario (nome, email, senha, data_nascimento, cpf, telefone, endereco, data_cadastro) VALUES ('Srta. Maria Julia Jesus', 'manuelada-mata@ig.com.br', '0p#il5sE&IQv', '1960-04-30', '04627138571', '55319566248', 'Via Lívia Moura, 43, Nossa Senhora Do Rosário, 01120113 Correia de das Neves / MA', '2024-06-16 18:12:15');
INSERT INTO usuario (nome, email, senha, data_nascimento, cpf, telefone, endereco, data_cadastro) VALUES ('Heitor Campos', 'calebe88@yahoo.com.br', '0ckppQak&eS8', '1972-09-13', '53678901484', '55219071783', 'Praça Samuel Pereira, 45, Sport Club, 26380885 Cardoso / AL', '2024-10-07 16:28:01');
INSERT INTO usuario (nome, email, senha, data_nascimento, cpf, telefone, endereco, data_cadastro) VALUES ('Yasmin Ramos', 'usilveira@monteiro.br', 'L2(f9I#2P6P%', '1963-08-03', '12890564398', '55119124152', 'Via Pedro Henrique Campos, Santa Amelia, 17941017 da Costa da Mata / RJ', '2024-09-15 18:14:34');
INSERT INTO usuario (nome, email, senha, data_nascimento, cpf, telefone, endereco, data_cadastro) VALUES ('Davi Luiz Martins', 'ana76@ig.com.br', 'wIGLw#Fj(b0z', '1999-10-21', '23186795095', '55619714159', 'Trecho Juliana Cardoso, 4, Novo Aarão Reis, 41517767 Cunha / CE', '2024-05-15 23:58:56');
INSERT INTO usuario (nome, email, senha, data_nascimento, cpf, telefone, endereco, data_cadastro) VALUES ('Emilly Ferreira', 'martinspedro-lucas@araujo.br', '@_8G5osNw9PN', '2003-01-12', '60138597286', '55819452222', 'Conjunto Raul da Cunha, 16, Santa Cecilia, 70328139 Viana das Pedras / CE', '2024-08-16 02:02:36');
INSERT INTO usuario (nome, email, senha, data_nascimento, cpf, telefone, endereco, data_cadastro) VALUES ('Bianca Costa', 'wcampos@gmail.com', 'Y^r(bMn02pMz', '1962-06-04', '80675291402', '55849731058', 'Vale Luiza Jesus, 79, Conjunto Bonsucesso, 61587656 Fernandes / RO', '2024-10-05 11:55:42');
INSERT INTO usuario (nome, email, senha, data_nascimento, cpf, telefone, endereco, data_cadastro) VALUES ('Thiago Ferreira', 'viniciusalmeida@fernandes.org', 'ldk_Pu1u6#6y', '2002-10-11', '58291346089', '55319318396', 'Via de da Cunha, 91, Santa Sofia, 11403903 Castro / PR', '2024-03-09 02:18:51');
INSERT INTO usuario (nome, email, senha, data_nascimento, cpf, telefone, endereco, data_cadastro) VALUES ('Luiz Henrique Mendes', 'sophiamonteiro@bol.com.br', '$p4YzETgZS3H', '1964-07-17', '16429738078', '55849258558', 'Trevo Ferreira, 3, Custodinha, 33979-917 Nunes do Amparo / MA', '2024-01-19 07:12:17');
INSERT INTO usuario (nome, email, senha, data_nascimento, cpf, telefone, endereco, data_cadastro) VALUES ('Sr. Benjamin Mendes', 'melojoaquim@das.com', '+pIoQuLr4)5D', '2001-05-12', '08365194775', '55819606826', 'Viaduto de da Costa, 57, Centro, 73161757 Carvalho de Goiás / RS', '2024-01-03 21:03:35');
INSERT INTO usuario (nome, email, senha, data_nascimento, cpf, telefone, endereco, data_cadastro) VALUES ('Clara da Cruz', 'upereira@pinto.br', ')90IMmK*PaZh', '1969-08-31', '30594167884', '55519161772', 'Jardim Melo, 4, Baleia, 22893024 Costela / GO', '2024-08-11 06:23:39');
INSERT INTO usuario (nome, email, senha, data_nascimento, cpf, telefone, endereco, data_cadastro) VALUES ('Dr. Leonardo Jesus', 'bduarte@nunes.net', '%bIfENyBeH9u', '1995-10-20', '41835279619', '55849744016', 'Condomínio Ryan Almeida, 623, Barão Homem De Melo 2ª Seção, 00010-135 Ramos Alegre / GO', '2024-04-03 23:15:58');
INSERT INTO usuario (nome, email, senha, data_nascimento, cpf, telefone, endereco, data_cadastro) VALUES ('Mariane Martins', 'levimoura@da.br', 'Y)2j1CL$Lboj', '1980-01-28', '23457960810', '55419453206', 'Distrito de Carvalho, 93, Boa Viagem, 24488630 da Rosa / PA', '2024-04-04 01:08:32');
INSERT INTO usuario (nome, email, senha, data_nascimento, cpf, telefone, endereco, data_cadastro) VALUES ('Sra. Sophie Aragão', 'ribeiromaite@uol.com.br', '%0Lov7lxd1MK', '1965-05-31', '16523840905', '55849752849', 'Esplanada Maria Fernanda Correia, 21, Inconfidência, 02343697 Rezende / SP', '2024-04-25 11:32:41');
INSERT INTO usuario (nome, email, senha, data_nascimento, cpf, telefone, endereco, data_cadastro) VALUES ('Elisa Castro', 'brunafogaca@gmail.com', 'v_+5sCOb%Md^', '1979-10-21', '21895643791', '55319847237', 'Área da Cunha, 23, Vila São Gabriel Jacui, 50431-580 Ferreira do Oeste / PA', '2024-05-24 00:20:31');
INSERT INTO usuario (nome, email, senha, data_nascimento, cpf, telefone, endereco, data_cadastro) VALUES ('Gustavo da Rocha', 'ana-liviarocha@farias.com', 'OHU5)hYa&4cT', '1966-04-14', '58107326903', '55219560474', 'Colônia de Souza, Vila Mantiqueira, 48627711 Gonçalves do Sul / PE', '2024-01-18 11:04:43');
INSERT INTO usuario (nome, email, senha, data_nascimento, cpf, telefone, endereco, data_cadastro) VALUES ('Breno Pinto', 'freitasstephany@bol.com.br', 'X_odg5NjDqa)', '1977-02-25', '85946271067', '55619126354', 'Quadra Marcos Vinicius Moraes, 41, Camargos, 27155251 Ferreira do Amparo / BA', '2024-03-10 08:49:20');
INSERT INTO usuario (nome, email, senha, data_nascimento, cpf, telefone, endereco, data_cadastro) VALUES ('André Azevedo', 'marcelo27@da.br', 's8ClB(^X+4mr', '1977-07-08', '60725918411', '55419748540', 'Recanto de Rezende, 98, Gutierrez, 37994-592 Cardoso da Mata / MA', '2024-10-02 08:19:20');
INSERT INTO usuario (nome, email, senha, data_nascimento, cpf, telefone, endereco, data_cadastro) VALUES ('Guilherme Viana', 'lsilva@ig.com.br', 'iX%yv7Gj@oku', '1998-07-15', '53680219768', '55419570898', 'Ladeira de Campos, Vila Aeroporto Jaraguá, 69865587 Fogaça / SP', '2024-06-24 07:50:17');