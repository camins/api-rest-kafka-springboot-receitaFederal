# Microsserviço Receita Federal

Você já precisou tirar uma certidão de nascimento de um bebê que acabou de nascer? Já observou que o CPF é gerado nesse momento? Já pensou em como é gerado esse CPF e quem mantem esses registros?

Esse microsserviço desempenha o papel da receita federal, gerando um CPF valido e único para cada requisição.

## Ferramentas

- Java 11
- Spring boot
- MySQL
- Apache Kafka
- Docker (docker-compose)

## Diagrama da aplicação

![Diagrama da aplicacao](/.github/diagram.png)

## Comunicação entre os microsserviços

A [API Rest do cartório](https://github.com/camins/api-rest-kafka-springboot-cartorio) possui um método POST no caminho (url:porta/cartorio/) que recebe um objeto JSON com a seguinte estrutura:

    {
      "nome": "nome",
      "cidade": "cidade",
      "estado": "estado",
      "sexo": "sexo",
      "dataNascimento": "data e hora do nascimento"
    }

A API irá processar esses dados e enviar uma requisição para o tópico *cartorio-cpf-request* do servidor kafka. Dessa forma o microsserviço da receita federal que estará consumindo os dados recebidos nesse tópico, irá gerar o CPF e retornar um objeto JSON para o tópico *cpf-calculate-reply*.

## Bora testar

Para executar e realizar testes nesse projeto (*Spring Tool Suite*), é necessário:

 * Realizar o clone do projeto da [API Rest do cartório](https://github.com/camins/api-rest-kafka-springboot-cartorio);
 * Executar via terminal os containers docker do kafka e mySQL localizados na **pasta resources do projeto do cartório**, usando os seguintes comandos:

        docker-compose -f kafka-docker-compose.yml up -d
        docker-compose -f mysql-docker-compose.yml up -d
    
* Utilizando o software Insominia, faça uma requisição **POST** para o endereço *localhost:8080/cartorio/* passando o corpo do objeto JSON:

        {
          "nome": "Dona Florinda da Silva",
          "cidade": "Natal",
          "estado": "RN",
          "sexo": "Feminino",
          "dataNascimento": "1949-02-08T07:50:00"
        }
    
 É esperado que retorne um *status 201 created* com um objeto JSON contendo o objeto criado.
 
 * Para visualizar o CPF gerado, faça uma requisição **GET** para o endereço *localhost:8080/pessoa/{**id**}*, onde o campo *id* deve ser preenchido com o *id* retornado na resposta da requisição **POST**.

