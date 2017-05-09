# Introdução
Este projeto é uma prova de conceito para o desenvolvimento do novo gateway de
pagamentos

# Tecnologias

## Kubernetes
O Kubernetes foi utilizado para facilitar o processo de deploy dos containers da
poc.

# Projetos
As aplicações java encontram-se dentro da subpasta **/java/**  

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
