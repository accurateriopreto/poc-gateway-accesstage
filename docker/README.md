# Versões

- docker 17.05.0-ce
- docker-compose 1.12.0

# Inicialização dos containers
Para inicializar os container, execute o comando abaixo:

```shell
docker-compose up
```

Para reconstruir as imagens do docker, utilize o comando  
```shell
docker-compose build
```

Caso deseje remover os container criado pelo compose, utilize:
```shell
docker-compose rm
```


# Containers
O docker compose subirá os seguintes container

## Postgres
Container de banco de dados, configurações abaixo  
* Senha: 123456
* Porta: 5423
* Banco de dados: gatewaydb
* Usuário para conexão no banco de dados: gateway

