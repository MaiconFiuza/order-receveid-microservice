# order-received-microservice
microserviço de gerenciamento de pedido


## FIAP - Tech Challenge - Fase 4

### Sistema de gestão para seus estabelecimentos

Nessa quarta fase de entrega o objetivo é desenvolver um backend de Sistema de Gerenciamento de Pedidos Integrado
com Spring e Microsserviços dividido em 6 microserviços.

### Serviço de criação de pedido  
Serviço responsável por realizar o pedido e gerar o evento kafka do pedido criado

### Como rodar o projeto
Para rodar o projeto completo é necessário baixar os 6 microsserviços e rodar a partir do arquivo docker-compose que se encontra no repositório de [customer](https://github.com/MaiconFiuza/customer-microservice)

#### 1. Fazer o build dos containeres da aplicação:
Executar o seguinte comando:
    
    docker-compose up --build

#### 2. Executar a aplicação através dos containeres criados:
Executar o seguinte comando para inicializar os containeres da aplicação

    docker-compose up


Serviço de customer
Disponível na porta http://localhost:8083/

Link para a documentação das API's do projeto (OpenAPI):
[http://localhost:8083/swagger-ui/index.html](http://localhost:8083/swagger-ui/index.html)



### Cobertura de testes do projeto 
Para rodar a cobertura de testes do projeto é possível pelo comando mvn test, o report com a porcentagem de testes coberto estará no arquivo index dentro de `target\site\jacoco`
<img width="1224" height="497" alt="image" src="https://github.com/user-attachments/assets/0bfa37ab-183d-423e-8439-607d72751726" />




