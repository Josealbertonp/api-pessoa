# API Pessoa

## Descrição

Esta API foi construída com Spring Boot e permite gerenciar dados de pessoas. Através dessa API, é possível realizar operações CRUD (Create, Read, Update, Delete) para gerenciar as informações de pessoas, como nome, CPF, data de nascimento e email.

## Tecnologias

- **Java 17** ou superior
- **Spring Boot 3.2.5**
- **MySQL** como banco de dados
- **Swagger** para documentação da API
- **JUnit 5** e **Mockito** para testes automatizados

## Como rodar o projeto

### Pré-requisitos

Antes de executar o projeto, certifique-se de que você tem as seguintes ferramentas instaladas:

- Java 17 ou superior
- Gradle ou Maven
- Banco de dados MySQL

### Configuração do banco de dados MySQL

1. **Crie o banco de dados** no MySQL:

    ```sql
    CREATE DATABASE api_pessoa;
    ```

2. **Configuração da conexão no `application.properties`**:

   No arquivo `src/main/resources/application.properties`, configure a conexão com o seu banco de dados local:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/api_pessoa
    spring.datasource.username=root
    spring.datasource.password=senha
    ```

   *Altere a senha conforme necessário.*

### Como executar

1. Clone este repositório para sua máquina local:

    ```bash
    git clone https://github.com/Josealbertonp/api-pessoa.git
    ```

2. Navegue até o diretório do projeto:

    ```bash
    cd api-pessoa
    ```

3. Compile e execute a aplicação:

   Para **Gradle**:
    ```bash
    ./gradlew bootRun
    ```

   Caso utilize **Windows**, use o comando `gradlew` sem o `./`:
    ```bash
    gradlew bootRun
    ```

4. A API estará rodando em `http://localhost:8080`. Você pode testar os endpoints usando um cliente HTTP como o **Postman** ou diretamente pelo navegador (para os métodos GET).

## Endpoints

A API oferece os seguintes endpoints:

### 1. Listar todas as pessoas

- **Método**: `GET`
- **URL**: `/pessoas`
- **Descrição**: Retorna uma lista de todas as pessoas cadastradas no sistema.

### 2. Criar uma nova pessoa

- **Método**: `POST`
- **URL**: `/pessoas/cadastrar`
- **Descrição**: Cria uma nova pessoa no sistema. É necessário enviar os dados no corpo da requisição.

### 3. Buscar pessoa por ID

- **Método**: `GET`
- **URL**: `/pessoas/localizar/{id}`
- **Descrição**: Busca uma pessoa pelo seu ID.

### 4. Atualizar pessoa

- **Método**: `PUT`
- **URL**: `/pessoas/alterar/{id}`
- **Descrição**: Atualiza os dados de uma pessoa existente. Envie os dados no corpo da requisição.

### 5. Deletar pessoa

- **Método**: `DELETE`
- **URL**: `/pessoas/deletar/{id}`
- **Descrição**: Deleta uma pessoa do sistema pelo seu ID.

## Testes

Para rodar os testes automatizados, use o seguinte comando:

- **Gradle**:

    ```bash
    ./gradlew test
    ```

Ou, caso use o **Windows**:

```bash
gradlew test
