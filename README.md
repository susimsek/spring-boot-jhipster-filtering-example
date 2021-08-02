# Spring Boot Jhipster Filtering Example
  
<img src="https://github.com/susimsek/spring-boot-jhipster-filtering-example/blob/main/images/spring-boot-jhipster-filtering-example.png" alt="Spring Boot Jhipster Filtering Example" width="100%" height="100%"/> 

## Prerequisites

* Docker 19.03+
* Docker Compose 1.25+

## Build Docker Image

```sh
./mvnw -Pprod compile jib:dockerBuild
```

## Installation

```sh
docker-compose up -d 
```

## Used Technologies

* Spring Boot
* Postgresql
* Redis

### Shared Libraries

* Java 11 
* Spring Boot 2.4.7
* Jhipster Framework 7.1.0
* Spring Boot Starter Log4j2
* Spring Boot Starter Web
* Spring Boot Starter Actuator
* Spring Boot Starter Jpa
* Spring Boot Starter Cache
* Spring Boot Starter Test
* Liquibase Core
* Hibernate Jpamodelgen
* Hibernate Jcache
* Redisson
* Lombok
* Mapstruct
* Mapstruct Processor
* SpringDoc Openapi Ui
* SpringDoc Openapi Data Rest

### Dev Environment Libraries

* Spring Boot Starter Tomcat
* H2
* Dev Tools

### Prod Environment Libraries

* Spring Boot Starter Undertow
* Postgresql

### Plugins

* Spring Boot Maven Plugin
* Maven Compiler Plugin
* Maven Clean Plugin
* Jib Maven Plugin
* Liquibase Maven Plugin

### Features

* Swagger
* Audit
* Filtering
* Pagination
* Caching

## Swagger Support

You can access the SpringDoc ui from the following url.  

http://localhost:9090/swagger-ui.html

<img src="https://github.com/susimsek/spring-boot-jhipster-filtering-example/blob/main/images/springdoc-ui.png" alt="SpringDoc Ui" width="100%" height="100%"/> 