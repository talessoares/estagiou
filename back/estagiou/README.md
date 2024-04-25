# Passo a Passo para Configuração Inicial

* Abra a pasta <strong>back</strong> na sua IDE de desenvolvimento

* Crie o arquivo application.properties

```sh
cp estagiou/src/main/resources/application-template.properties estagiou/src/main/resources/application.properties
```

* Altere o usuário e a senha no arquivo criado

```sh
# Adicione o usuário do banco de dados
spring.datasource.username=

# Adicione a senha do banco de dados
spring.datasource.password=

# Adicione o segredo jwt (Você pode adicionar qualquer coisa)
jwt.secret=
```

* Crie o banco de dados com nome de <strong>estagiou</strong> através do pgAdmin ou qualquer outro método disponível

* Rode a aplicação