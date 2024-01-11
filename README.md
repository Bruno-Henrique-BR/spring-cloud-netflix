```markdown
# Microsserviços com Spring Cloud Netflix, RabbitMQ e Docker Compose

Este repositório contém a implementação de dois microsserviços, `microservico-produto` e `microservico-pagamento`, que se comunicam via RabbitMQ. Além disso, o projeto inclui um serviço de autenticação utilizando Spring Security e JWT, um servidor de descoberta (Eureka Server) e um gateway utilizando o Netflix Zuul. A base de dados utilizada é o MySQL.

## Tecnologias Utilizadas

- **Java 11:** Linguagem de programação principal utilizada no desenvolvimento dos microsserviços.
- **Spring Boot:** Framework utilizado para a criação rápida e eficiente dos microsserviços.
- **Spring Security:** Implementação de segurança utilizada para autenticação nos microsserviços.
- **MySQL:** Sistema de gerenciamento de banco de dados relacional utilizado para armazenamento de dados.
- **RabbitMQ:** Plataforma de mensagens utilizada para a comunicação assíncrona entre os microsserviços.
- **Eureka Server:** Serviço de descoberta utilizado para registro e localização dinâmica dos microsserviços.
- **Netflix Zuul Gateway:** Componente utilizado como gateway para roteamento eficiente das solicitações.

## Microsserviços

### `microservico-produto`
- Responsável por lidar com operações relacionadas a produtos.
- Comunica-se com o `microservico-pagamento` para processar pagamentos.
- Utiliza Spring Security para autenticação e JWT para segurança.

### `microservico-pagamento`
- Encarregado de processar pagamentos.
- Comunica-se com o `microservico-produto` para obter informações sobre os produtos.
- Também utiliza Spring Security e JWT para autenticação.

## Serviços Adicionais

### Serviço de Autenticação
- Implementa autenticação utilizando Spring Security e gera tokens JWT para acesso seguro aos microsserviços.

### Eureka Server
- Oferece serviços de descoberta, permitindo que os microsserviços se registrem e se encontrem dinamicamente.

### Netflix Zuul Gateway
- Funciona como um gateway para os microsserviços, roteando as solicitações de forma eficiente.
- Adiciona uma camada de segurança ao gerenciar o acesso aos microsserviços.

## Configuração e Execução

Certifique-se de ter o Docker e Docker Compose instalados em seu ambiente.

1. **Clone este repositório:**
   ```bash
   git clone https://github.com/Bruno-Henrique-BR/spring-cloud-netflix
   cd spring-cloud-netflix
   ```

2. **Execute o Docker Compose para iniciar os serviços:**
   ```bash
   docker-compose up -d
   ```

   Isso iniciará os contêineres para os microsserviços, RabbitMQ, MySQL, Serviço de Autenticação, Eureka Server e Zuul Gateway.

3. **Os serviços estarão disponíveis nos seguintes endereços:**
   - `microservico-produto`: [http://localhost:8081](http://localhost:8081)
   - `microservico-pagamento`: [http://localhost:8082](http://localhost:8082)
   - Serviço de Autenticação: [http://localhost:8083](http://localhost:8083)
   - Eureka Server: [http://localhost:8761](http://localhost:8761)
   - Zuul Gateway: [http://localhost:8765](http://localhost:8765)

Agora você pode explorar e testar a comunicação entre os microsserviços utilizando o RabbitMQ, além de utilizar o Zuul como gateway e o Eureka para descoberta de serviços. Certifique-se de adaptar as configurações conforme necessário para o seu ambiente específico.
```
