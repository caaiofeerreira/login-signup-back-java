# Login / Signup - BackEnd

### | Tecnologias Utilizadas:
- **Spring Boot**:
    - `spring-boot-starter-data-jpa`
    - `spring-boot-starter-security`
    - `spring-boot-starter-web`
    - `spring-boot-devtools`
    - `spring-boot-starter-validation`

- **Lombok**

- **Auth0 Java JWT**

- **MySQL**
    - `mysql-connector-j`: Driver JDBC para MySQL (runtime).

---

## Descrição

Este projeto é uma aplicação RESTful desenvolvida com Spring Boot que fornece funcionalidades de autenticação e registro de usuários. A aplicação permite que usuários se registrem e façam login, gerando um token JWT para acesso seguro a recursos protegidos.

### | Funcionalidades

- **Registro de Usuário**: O endpoint `/register` permite que novos usuários se registrem fornecendo nome, email e senha. O sistema valida os dados de entrada e armazena o usuário no banco de dados após a criptografia da senha.

- **Login de Usuário**: O endpoint `/login` permite que os usuários façam login utilizando email e senha. Se as credenciais estiverem corretas, um token JWT é gerado e retornado, permitindo que o usuário acesse recursos protegidos.

### | Validações

O sistema inclui diversas validações durante o registro:
- O nome do usuário deve conter apenas letras e caracteres especiais permitidos.
- O email deve ser único; se já estiver cadastrado, uma exceção é lançada.
- A senha deve ter pelo menos 6 caracteres.

### | Estrutura do Código

- **UserController**: Controlador responsável por gerenciar as requisições de login e registro.
- **AuthenticationServiceImpl**: Implementa a interface `UserDetailsService` para carregar os detalhes do usuário durante a autenticação.
- **UserServiceImpl**: Contém a lógica de registro do usuário, incluindo validações.
- **ValidateUserRegister**: Componente que valida os dados do usuário durante o registro.

---

Link: [Login / Signup - FrontEnd](https://github.com/caaiofeerreira/login-signup-front-angular?tab=readme-ov-file)