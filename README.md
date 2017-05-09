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
- POST http://192.168.99.100:30808/gateway-core-service

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

Para execução do projeto no ambiente de desenvolvimento utilizando SpringBoot é
possível passar o perfil **local**, isso fará com que a aplicação utilize um
banco de dados embutido, ao invés de tentar se conectar ao postgres. Exemplo da
chamada para ambiente local:  

```shell
mci spring-boot:run -Dspring.profiles.active=local
```


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
