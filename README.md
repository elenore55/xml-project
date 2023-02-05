# XML and web services project - Team 22

## Requirements
* [Java 19](https://www.oracle.com/java/technologies/downloads/#java19)
* [Maven](https://maven.apache.org/download.cgi)
* [Spring Boot 2.7.7](https://spring.io/projects/spring-boot)
* [Node.js 16.16.0](https://nodejs.org/en/)
* [Docker](https://www.docker.com/)

## Setup

### eXist-db and Apache Jena
1. Open `cmd` and navigade to the root folder of the project
2. Run command `docker compose up -d`
3. Go to <http://localhost:8083> and enter credentials for Apache Jena:
    > `username: admin`
    
    > `password: admin`
4. Go to Apache Jena dashboard and create following persistent datasets:
    `AutorskoPravoDataset`, `PatentDataset` and `ZigDataset`
    
### User service
1. Open `cmd` and navigate to `xml-project/users`
2. Run command `mvn spring-boot:run`

### *Autorsko pravo* service
1. Open `cmd` and navigate to `xml-project/autorsko_pravo`
2. Run command `mvn spring-boot:run`

### *Patent* service

1. Open `cmd` and navigate to `xml-project/patent`
2. Run command `mvn spring-boot:run`

### *Žig* service

1. Open `cmd` and navigate to `xml-project/zig`
2. Run command `mvn spring-boot:run`

### Frontend
1. Open `cmd` and navigate to `xml-project/frontend/project`
2. Install node modules with `npm install`
3. Run command `npm run serve -- --port 8082`


Go to <http://localhost:8082> to start using the application.
