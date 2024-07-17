# Fórum Hub

Bem-vindo ao Fórum Hub, um projeto desenvolvido para criar uma API REST para um fórum utilizando Spring. Este projeto foi desenvolvido como parte do Challenge Back End da Alura.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- Spring Security
- Maven
- MySQL
- Postman
- JWT (JSON Web Token)

## Funcionalidades

- **Criação de Usuário:** Permite a criação de um novo usuário.
  - Endpoint: `POST /v1/signin`
- **Login:** Autenticação de usuário e geração de token JWT.
  - Endpoint: `POST /v1/login`
- **CRUD de Tópicos:**
  - Criar novo tópico
    - Endpoint: `POST /v1/posts`
  - Listar todos os tópicos
    - Endpoint: `GET /v1/feed`
  - Consultar um tópico específico
    - Endpoint: `GET /v1/posts/{id}`
  - Atualizar um tópico (somente pelo autor)
    - Endpoint: `PUT /v1/posts/{id}`
  - Deletar um tópico (somente pelo autor ou administrador)
    - Endpoint: `DELETE /v1/posts/{id}`

## Requisitos

- JDK 17 ou superior
- Maven 3.9.6 ou superior
- MySQL

## Configuração do Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/mrpauloricardo/forum-hub.git
   cd forum-hub
   ```

2. Configure o banco de dados MySQL:
    - Crie um banco de dados chamado `forum-hub`.
    - Atualize as configurações do banco de dados no arquivo `src/main/resources/application.properties` com suas credenciais do MySQL.

3. Compile e execute o projeto:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## Documentação da API

### Criação de Usuário

- **Endpoint:** `POST /v1/signin`
- **Descrição:** Cria um novo usuário.
- **Corpo da Requisição:**
  ```json
  {
    "username": "paulo",
    "email": "paulo@email.com",
    "password": "1234"
  }
  ```

### Login

- **Endpoint:** `POST /v1/login`
- **Descrição:** Realiza o login de um usuário e retorna um token JWT.
- **Corpo da Requisição:**
  ```json
  {
    "username": "paulo",
    "password": "1234"
  }
  ```

### CRUD de Tópicos

- **Criar Tópico:**
    - **Endpoint:** `POST /v1/posts`
    - **Descrição:** Cria um novo tópico.
    - **Corpo da Requisição:**
      ```json
      {
        "title": "Título do Tópico",
        "content": "Conteúdo do Tópico"
      }
      ```

- **Listar Tópicos:**
    - **Endpoint:** `GET /v1/feed`
    - **Descrição:** Lista todos os tópicos.

- **Consultar Tópico Específico:**
    - **Endpoint:** `GET /v1/posts/{id}`
    - **Descrição:** Consulta um tópico específico pelo ID.

- **Atualizar Tópico:**
    - **Endpoint:** `PUT /v1/posts/{id}`
    - **Descrição:** Atualiza um tópico existente (somente pelo autor).
    - **Corpo da Requisição:**
      ```json
      {
        "title": "Novo Título",
        "content": "Novo Conteúdo"
      }
      ```

- **Deletar Tópico:**
    - **Endpoint:** `DELETE /v1/posts/{id}`
    - **Descrição:** Deleta um tópico existente (somente pelo autor ou administrador).
