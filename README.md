# Introdução
Este projeto é uma prova de conceito para o desenvolvimento do novo gateway de
pagamentos

# Tecnologias

## Kubernetes
O Kubernetes foi utilizado para facilitar o processo de deploy dos containers da
poc.

# Projetos
As aplicações java encontram-se dentro da subpasta **/java/**  

## postgres
O banco de dados utilizado no projeto foi o postgres, ele foi disponibilizado no
kubernetes e pode ser acessado externamente a partir das configurações abaixo:  

* hostname: endereço ip do service do kubernetes do ambiente implantado.
* port: 32574
* database: gatewaydb
* username: gateway
* password: 123456

## gateway-spring-core
Projeto que disponibiliza uma api rest integrada com o banco postgres. Essa api
permite a chamada de um metodo POST que simula um pagamento e salva os dados
referente a ele no banco.  

Exemplo:
- POST http://192.168.99.100:30808/operations

```json
{
   "orderNumber": "123",
   "orderTotal": 100.10
}
```  

exemplo de resposta:  
```json
{
  "orderNumber": "123",
  "orderTotal": 100.1,
  "orderStatus": "pago",
  "transactionDate": 1494339174898,
  "authorizationCode": "783854"
}
```  
Os campos *orderStatus* e *authorizationCode* tem resposta randômica.  


- GET http://192.168.99.100:30808/operations/{id}  
Faz a busca pelo pedido informado pelo campo *{id}*  
Caso não encontre resultado retorno o codigo http 404 e um json como no exemplo
abaixo:  

```json
{
  "orderNumber": "123",
  "orderTotal": null,
  "orderStatus": "not_found",
  "transactionDate": null,
  "authorizationCode": null
}
```  

Caso a busca retorne resultado, retorna o código 200 e o json de resposta  
```json
{
  "orderNumber": "123",
  "orderTotal": 100.1,
  "orderStatus": "pago",
  "transactionDate": 1494353901554,
  "authorizationCode": "522162"
}
```

Para execução do projeto no ambiente de desenvolvimento utilizando SpringBoot é
possível passar o perfil **local**, isso fará com que a aplicação utilize um
banco de dados embutido, ao invés de tentar se conectar ao postgres. Exemplo da
chamada para ambiente local:  

```shell
mvn clean install spring-boot:run -Dspring.profiles.active=local
```

**OBS: os endereços IPs precisam ser obtidos do kubernetes no qual a POC foi
implantada**

## gateway-eureka
Este é servidor responsável pelo ponto de registros dos clientes.
Consumidores que posteriormente consumidores possam descobrir estes clientes.

Para realizar o build do projeto execute o seguinte comando.
```shell
mvn clean package
```

Para subir o servidor execute o seguinte comando:
```shell
mvn spring-boot:run -Dspring.profiles.active=devLocal
```
Quando o servidor se encontrar online é possível visualizar as aplicação registradas como cliente na seguinte url

- POST http://localhost:8761/

## gateway-client-service
Cliente responsável por acesso ao serviços da api de gateway. Este quando executado se auto registra no servidor gateway-eureka. 
Quando um consumidor realizar uma requisição de discovery no servidor gateway-eureka é retornado um endereço,
para que se possa realizar a requisição

Para realizar o build do projeto execute o seguinte comando.
```shell
mvn clean package
```

Para subir o cliente execute o seguinte comando:

```shell
mvn spring-boot:run -Dspring.profiles.active=devLocal
```
Este subirá na porta padrão definida no application.yalm 7111, é possível subir mais de uma instância executando o comando acima,
alterando a porta como no exemplo abaixo

```shell
mvn spring-boot:run -Dspring.profiles.active=devLocal -Dport=7222
```
Neste ponto é possível testar o cliente, realize um post para 

- POST http://localhost:7222/transactions

```json
{
   "orderNumber": "123",
   "orderTotal": 100.10
}
```  

exemplo de resposta:  
```json
{
  "orderNumber": "123",
  "orderTotal": 100.1,
  "orderStatus": "pago",
  "transactionDate": 1494339174898,
  "authorizationCode": "783854"
}
```  
## gateway-consumer
Este projeto fica responsável por consumir os clientes registrados no servidor **gateway-eureka**. Este consumer se utiliza
do ribbon para realizar o load-balance do clientes expostos pelo **gateway-eureka** e também do zuul para realizar 

# Instalação

## Minikube
Foi utilizado para facilitar e agilizar o desenvolvimento em ambiente local, as
instruções de instalações estão em:
* [Minikube](https://github.com/kubernetes/minikube)

### YALM dos Containers
Os arquivos YALM de configuração dos container para o Kubernetes encontram-se em
**/kubernetes**
- postgres.yml  
Cria um **Deployment** e um **Service** para uma base Postgre
- gateway-core-service.yml  
Cria **Deployment** e **Service** para api Rest.
- eureka.yml  
Cria **Deployment** e **Service** do discovery service do eureka.
- gateway-cliente-service.yml  
Cria **Deployment** e **Service** do client que se registra no eureka.
- gateway-consumer.yml
Cria **Deployment** e **Service** do consumer

# Testando a aplicação
O aplicativo pode ser testado utilizando qualquer ferramenta capaz de consumir
uma api rest (Soapui, Postman...)  

Disponibilizamos um projeto de exemplo do postman dentro da pasta */postman* com
o request necessários para criar e consultar um Pedido através da api rest. Este
exemplo pode ser importado para dentro da ferramente para ser utilizado.  

*OBS: os endereços de IP devem ser ajustados para o kubernetes onde o projeto
gateway-consumer foi importando*  

### Operações disponíveis
   * POST - salva os dados de um pedido na base de dados
   * GET - faz a consulta pelo OrderNumber informado do método POST
