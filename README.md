# Introdução
Este projeto é uma prova de conceito para o desenvolvimento do novo gateway de
pagamentos

# Tecnologias

## Kubernetes
O Kubernetes foi utilizado para facilitar o processo de deploy dos containers da
poc.

# Instalação

## Minikube
Foi utilizado para facilitar e agilizar o desenvolvimento em ambiente local, as
instruções de instalações estão em:
* [Minikube](https://github.com/kubernetes/minikube)

### YALM dos Containers
Os arquivos YALM de configuração dos container para o Kubernetes encontram-se em
**/kubernetes**
- kubernetes.yml  
Cria um **Deployment** e um **Service** para uma base Postgre
