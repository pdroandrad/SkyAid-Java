# SkyAid 🚁

O SkyAid é uma solução inovadora que utiliza drones para resgate de vítimas e entrega de suprimentos em áreas isoladas por desastres como enchentes e deslizamentos. Este repositório contém a API REST desenvolvida em Java com Spring Boot, que gerencia drones, missões, operadores e registros de sensores, garantindo uma operação coordenada e eficiente. A aplicação permite o cadastro de operadores, gerenciamento de missões e monitoramento em tempo real por meio de sensores.

## Integrantes
- Pedro Abrantes Andrade | RM558186
- Ricardo Tavares de Oliveira Filho | RM556092
- Victor Alves Carmona | RM555726

## Tecnologias Utilizadas
- Java 21
- Spring Boot 3.5.0
- Maven
- Spring Data JPA
- Bean Validation (Jakarta Validation)
- Oracle Database
- Swagger/OpenAPI
- Render (deploy em nuvem)

## Estrutura do Projeto

| Pacote         | Descrição                                           |
| -------------- | --------------------------------------------------- |
| `model`        | Entidades JPA mapeando as tabelas do banco de dados |
| `repository`   | Interfaces Spring Data JPA para persistência        |
| `service`      | Camada de regras de negócio                         |
| `controller`   | Endpoints REST da API                               |
| `dto.request`  | Objetos de requisição (entrada de dados)            |
| `dto.response` | Objetos de resposta (saída de dados)                |
| `util`         | Enumerações (StatusDrone, StatusMissao, etc.)       |
| `exception`    | Tratamento global de exceções                       |

## Endpoints da API

### Drones (/api/drones)
| Método | Endpoint                      | Descrição                                         |
| ------ | ----------------------------- | ------------------------------------------------- |
| GET    | `/api/drones`                 | Lista todos os drones                             |
| GET    | `/api/drones/{id}`            | Busca drone por ID                                |
| GET    | `/api/drones/status/{status}` | Filtra drones por status (DISPONIVEL, EM\_MISSAO) |
| POST   | `/api/drones`                 | Cria um novo drone                                |
| PUT    | `/api/drones/{id}`            | Atualiza dados de um drone                        |
| DELETE | `/api/drones/{id}`            | Remove um drone                                   |


### Missões (/api/missoes)
| Método | Endpoint                       | Descrição                                            |
| ------ | ------------------------------ | ---------------------------------------------------- |
| GET    | `/api/missoes`                 | Lista todas as missões                               |
| GET    | `/api/missoes/{id}`            | Busca missão por ID                                  |
| GET    | `/api/missoes/status/{status}` | Filtra missões por status (EM\_ANDAMENTO, CONCLUIDA) |
| GET    | `/api/missoes/tipo/{tipo}`     | Filtra missões por tipo (RESGATE, ENTREGA)           |
| POST   | `/api/missoes`                 | Cria uma nova missão                                 |
| PUT    | `/api/missoes/{id}`            | Atualiza dados de uma missão                         |
| DELETE | `/api/missoes/{id}`            | Remove uma missão                                    |


### Operadores (/api/operadores)
| Método | Endpoint                | Descrição                     |
| ------ | ----------------------- | ----------------------------- |
| GET    | `/api/operadores`       | Lista todos os operadores     |
| GET    | `/api/operadores/{id}`  | Busca operador por ID         |
| POST   | `/api/operadores`       | Cria um novo operador         |
| POST   | `/api/operadores/login` | Login de operador             |
| PUT    | `/api/operadores/{id}`  | Atualiza dados de um operador |
| DELETE | `/api/operadores/{id}`  | Remove um operador            |


### Sensores (/api/sensores)
| Método | Endpoint                    | Descrição                                     |
| ------ | --------------------------- | --------------------------------------------- |
| GET    | `/api/sensores`             | Lista todos os registros de sensores          |
| GET    | `/api/sensores/{id}`        | Busca registro por ID                         |
| GET    | `/api/sensores/tipo/{tipo}` | Filtra registros por tipo (TEMPERATURA, etc.) |
| POST   | `/api/sensores`             | Cria um novo registro de sensor               |
| PUT    | `/api/sensores/{id}`        | Atualiza dados de um registro                 |
| DELETE | `/api/sensores/{id}`        | Remove um registro de sensor                  |

## Como Executar o Projeto

### 1. Pré-requisitos
- Java 21 instalado
- Maven instalado
- Acesso ao banco Oracle (configurar no `application.properties`)

### 2. Clone do projeto do Github
Executar os seguintes comandos no terminal:

```
git clone https://github.com/pdroandrad/sky-aid.git
cd sky-aid
```

### 3. Configuração
Edite o arquivo `application.properties` com suas credenciais:

```properties
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### 4. Execução
Executar o seguinte comando no terminal:
```
./mvnw spring-boot:run
```
A aplicação estará disponível em: `http://localhost:8080`.
Acesse a documentação Swagger em: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### 5. Exemplos json para testes de endpoints

#### Criar Operador
```
{
  "nome": "João Silva",
  "email": "joao.silva@example.com",
  "senha": "123456",
  "idBase": 1
}

```

#### Criar Drone
```
{
  "identificador": "DRONE-X1",
  "modelo": "Phantom Pro",
  "status": "DISPONIVEL",
  "operadorId": 1
}
```

#### Criar Missão
```
{
  "tipo": "RESGATE",
  "status": "EM_ANDAMENTO",
  "descricao": "Resgate de vítimas na zona norte",
  "destinoLatitude": -23.5505,
  "destinoLongitude": -46.6333,
  "droneId": 1
}
```

#### Criar Registro de Sensor
```
{
  "tipo": "TEMPERATURA",
  "valor": 38.5,
  "dataHora": "2025-05-30T10:00:00",
  "latitude": -23.5505,
  "longitude": -46.6333,
  "droneId": 1
}
```

### 6. Deploy na nuvem

Fizemos o deploy na nuvem através do `render.com` onde a API pode ser acessar através do link [https://skyaid-java.onrender.com/api/](https://skyaid-java.onrender.com/api/) + endpoint da entidade a ser acessada (`drones`, `operadores`, `missoes` ou `sensores`).
