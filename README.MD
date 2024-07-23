## Executando o serviço

### Compilando e gerando o jar

```
./gradlew clean build
```


### Rodando o projeto
Para rodar a aplicação vocês deve primeiro rodar as dependências: o banco relacional Mysql e o 
banco não relacional em memória Redis, para isso rode os seguintes comandos.

```
docker-compose up -d mysql redis
```

Depois rode o comando para a seguir para subir a aplicação:

```
./gradlew bootRun
```


## Realizando uma transação

### Usando as apis

Para adicionar um estabelecimento conhecido ao banco em memória, use o comando:

```
curl --location 'http://localhost:8080/merchants' \
--header 'Content-Type: application/json' \
--data '{
	"id": "1",
	"name": "UBER EATS                   SAO PAULO BR",
	"mcc": "5811"
}'
```

Para autorizar uma transação basta fazer uma requisição como esta:

```
curl --location 'http://localhost:8080/transactions' \
--header 'Content-Type: application/json' \
--data '{
	"account": "4567",
	"totalAmount": 10.00,
	"mcc": "5411",
	"merchant": "UBER EATS                   SAO PAULO BR"
}'
```


### Rodando os testes unitários
Para rodar os testes unitários:

```
./gradlew test
```

### Rodando os testes de integração
Para rodar os testes de integração, primeiro certifique-se que as dependências do serviço não estão rodando:

```
docker-compose down
```

Por fim, rode os testes:

```
./gradlew integrationTest
```
