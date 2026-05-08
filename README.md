# Pixel Character Creator Backend

Spring Boot backend for a pixel character creator project.

## Tech stack

- Java 21
- Spring Boot
- PostgreSQL
- Spring Data JPA
- OAuth2 Resource Server / Keycloak
- Swagger UI

## Start

1. Create PostgreSQL database in pgAdmin 4: pixel_character_creator

2. Start Keycloak and create realm:

```text
pixel-character-creator
```

3. Create roles:

```text
role_user
role_admin
```

4. Check `src/main/resources/application.yml` and adjust correct data: postgres username & password, ports.

5. Run:

```bash
mvn spring-boot:run
```

In case the JavaVersion Error occurs -> delete target folder and then run:
```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 26)
export PATH=$JAVA_HOME/bin:$PATH
mvn clean spring-boot:run
```

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```
