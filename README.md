# API Pessoa

## Sumário
- [Descrição](#descrição)
- [Tecnologias](#tecnologias)
- [Como rodar o projeto](#como-rodar-o-projeto)
  - [Pré-requisitos](#pré-requisitos)
  - [Usando Docker e Docker Compose](#usando-docker-e-docker-compose)
  - [Configuração manual do banco de dados](#configuração-manual-do-banco-de-dados-caso-não-use-docker)
  - [Como executar](#como-executar)
- [Endpoints](#endpoints)
- [Documentação com Swagger](#documentação-com-swagger)
- [Testes](#testes)

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

- **Docker** e **Docker Compose**
- **Java 17** ou superior
- **Gradle** ou **Maven**

### Usando Docker e Docker Compose

Se você deseja rodar a aplicação e o banco de dados de forma simples com Docker, basta seguir os seguintes passos:

1. **Clone este repositório para sua máquina local**:

    ```bash
    git clone https://github.com/Josealbertonp/api-pessoa.git
    ```

2. **Navegue até o diretório do projeto**:

    ```bash
    cd api-pessoa
    ```

3. **Configure o arquivo `.env`**

   Copie o arquivo `.env_example` e renomeie para `.env`. Depois, edite as variáveis de ambiente com os valores adequados para o seu projeto:


4. **Construa e inicie os containers com o Docker Compose**:

   Execute o comando abaixo para construir e iniciar a aplicação e o banco de dados MySQL:

    ```bash
    docker compose up --build
    ```

   Esse comando irá:

   - Construir as imagens necessárias
   - Rodar a aplicação Spring Boot
   - Rodar o banco de dados MySQL

   Os containers serão iniciados e você poderá acessar a aplicação em `http://localhost:8080` e o banco de dados MySQL em `localhost:3306`.


5. **Verifique os logs**:

   Após rodar o comando, você pode monitorar os logs dos containers com:

    ```bash
    docker compose logs -f
    ```

5. **Para parar os containers**, você pode usar:

    ```bash
    docker compose down
    ```

### Configuração manual do banco de dados (caso não use Docker)

Se você não usar o Docker, siga os passos abaixo para configurar o banco de dados manualmente:

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

## Documentação com Swagger

O Swagger está disponível para facilitar a visualização e teste da API. Para acessar a interface interativa do Swagger UI, utilize:

- **URL**: [`http://localhost:8080/swagger-ui.html`](http://localhost:8080/swagger-ui.html)

Através dessa interface, você pode visualizar todos os endpoints disponíveis, testar requisições diretamente pelo navegador e obter detalhes sobre os parâmetros e respostas.

### Exemplo da interface Swagger UI

> *![image](https://github.com/user-attachments/assets/f518a707-31a9-43c3-a03b-4956ac46c980)*

## Testes

O projeto conta com testes automatizados para garantir o correto funcionamento das principais funcionalidades da API. Os testes foram desenvolvidos utilizando **JUnit 5** e **Mockito**, permitindo simular o comportamento dos serviços e repositórios sem depender de um banco de dados real.  

Os testes estão organizados para validar tanto a **camada de serviço** quanto a **camada de controle (controller)**, garantindo que a lógica de negócios e as respostas dos endpoints estejam corretas.  

### O que os testes validam?

Os testes incluem os seguintes cenários:

1. **Testes da camada de Controller (`PessoaControllerTest`)**  
   - Garantem que os endpoints retornam os códigos HTTP corretos.  
   - Validam se os métodos chamam corretamente os serviços necessários.  
   - Simulam diferentes respostas da camada de serviço para verificar se a API se comporta corretamente diante de erros e sucessos.  
   - Exemplo: ao listar todas as pessoas, o teste verifica se o status de resposta é **200 OK** e se a lista retornada tem os itens esperados.  

2. **Testes da camada de Serviço (`PessoaServiceTest`)**  
   - Garantem que a lógica de negócio funciona corretamente.  
   - Testam a interação com o repositório e simulam diferentes retornos para validar fluxos distintos.  
   - Verificam se exceções são lançadas corretamente quando operações inválidas são realizadas.  
   - Exemplo: ao tentar buscar uma pessoa que não existe, o serviço deve lançar uma exceção do tipo `NotFoundException`.  

### Exemplos de cenários testados:

- **Cadastro de pessoa:** Garante que uma nova pessoa pode ser cadastrada e retorna o status **201 Created**.  
- **Listagem de pessoas:** Valida se a API retorna todas as pessoas cadastradas corretamente e lida com listas vazias.  
- **Busca por ID:** Confirma que a API retorna os dados corretos para um ID válido e lança erro quando o ID não existe.  
- **Atualização de pessoa:** Testa se a API consegue modificar os dados de uma pessoa existente e retorna **200 OK**.  
- **Exclusão de pessoa:** Verifica se a API remove corretamente uma pessoa e retorna **204 No Content** quando bem-sucedido.  

### Como executar os testes  

- **Gradle**:

    ```bash
    ./gradlew test
    ```

