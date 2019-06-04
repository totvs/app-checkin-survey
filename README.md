# TJF Checkin Survey :: Pesquisa de Satisfação

Fornece o serviço para envio das Pesquisas de satisfação.


## Modo de uso

Incluir a biblioteca como dependência no `pom.xml` da aplicação:

```xml
<dependency>
    <groupId>com.totvs.tjf</groupId>
    <artifactId>tjf-checkin-survey</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```


## Configurações (application.yml)

No arquivo de configurações _src/main/resources/application.yml_ teremos configurado as informações do banco de dados (PostgreSQL):

```
spring:
  datasource:
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://localhost:5432/checkin
    username: checkin
    password: checkin!123
```
    
OBS: Deve ser alterado de acordo com o banco criado.

## Subindo o Serviço

Para subirmos o serviço, basta executar como Spring Boot App.

#### Enviando para o Endpoint

De acordo com as configurações, o serviço será exposto na URL http://localhost:8180/api/v1/survey, sendo necessário enviar um POST, conforme exemplo a seguir:

```
{
	"email":"exemplo@totvs.com.br",
	"event":"Palestra",
	"note":5,
	"description":"Sugestão/critica da palestra"
}
```

* Os campos _email_ e _event_ são do tipo texto (String) e são obrigatórios. 
* O campo _note_ deve conter um número inteiro entre 0 e 5 e não pode ser nulo.
* Por ultimo, o campo _description_ também é do tipo texto (String) com no máximo 400 caracteres.

#### Validações

O serviço valida caso o mesmo Email já tenha informado uma nota em algum Evento, ou seja, o mesmo email não poderá responder a mesma pesquisa mais de uma vez.


## Licença

Copyright &copy; 2018 by [TOTVS](https://www.totvs.com)
