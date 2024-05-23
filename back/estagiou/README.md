# Passo a Passo para Configuração Inicial

## Requisitos

* Java 21

* Maven

## Passo a Passo

1. Abra a pasta <strong>back</strong> na sua IDE de desenvolvimento

2. Crie o arquivo application.yml

```sh
cp estagiou/src/main/resources/application-template.yml estagiou/src/main/resources/application.yml
```

Ou caso tenha aberto a pasta <strong>estagiou</strong>

```sh
cp back/estagiou/src/main/resources/application-template.yml back/estagiou/src/main/resources/application.yml
```

3. Adicione as informações ausentes indicadas no arquivo criado

4. Crie o banco de dados com nome de <strong>estagiou</strong> através do pgAdmin ou qualquer outro método disponível

5. Rode a aplicação

