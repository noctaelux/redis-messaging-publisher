# Spring Boot - Redis Messaging Publisher

## Requerimientos
- Java v11+
- Maven v3.8.3+
- Docker v20.10.21+ (Opcional)
- RedisInsight v2+ (Opcional) ([Docker Container](https://redis.com/es/redis-enterprise/redisinsight/))
- Redis v7.0.5 ([Docker Container](https://redis.com/es/redis-enterprise/redisinsight/))

## Instalación / Ejecución
Se debe instalar o tener algún servidor de Redis en ejecución, en la clase `RedisConfig` se pueden realizar 
modificaciones para ajustar los parámetros del canal, entre otras configuraciones, según las necesidades.

### Redis
Si ocupamos Redis dentro de un contenedor Docker, ejecutamos el siguiente comando:
```
docker run -d --name=redis -p 6379:6379 redis
```

### Spring boot
Para esta prueba de concepto, se ocupa la librería data-redis de spring boot, la cual proporcionará los componentes 
necesarios para trabajar con Redis, por lo que agregaremos las siguientes líneas dentro del archivo `pom.xml`:
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

Para levantar este proyecto de Spring Boot, vamos a la carpeta del proyecto y por medio de Maven:
```
mvn spring-boot:run
```

## Descripción
Esta prueba de concepto encola mensajes de tipo JSON desde un canal arbitrario `stream:cliente` de Redis, el cual se 
puede ver en el archivo de configuraciones `RedisConfig` donde además se pueden ver otras configuraciones de Redis.

El punto de partida en este PoC se encuentra en la clase controladora `ClienteController`, la cual está configurada como 
un `RestController` de Spring Boot, por lo que podremos enviar un HTTP POST de tipo JSON `Cliente` al endpoint 
`/api/v1/nuevo/cliente`.

El `RestController` realizará un llamado al servicio `ClienteService` por medio de su interfaz, el cual contiene la 
lógica de negocio suficiente para envolver el mensaje en formato JSON (por medio del bean `redisTemplate`) y encolarlo 
al canal de Redis por medio de la interfaz `Publisher`. Como `RedisPublisher` es la única implementación disponible, 
entonces `Publisher` la ocupará.

Adicionalmente, se ocupa la librería de Lombok para facilitar la lectura de
las clases y evitar boilerplate de código (constructores, getters, setters, patrón builder). Para mayor información sobre
el funcionamiento de esta librería: https://www.baeldung.com/intro-to-project-lombok 

Para visualizar logs sobre los mensajes que transitan sobre el queue, se sugiere instalar opcionalmente 
la herramienta RedisInsight.

### Fuentes
- https://spring.io/guides/gs/messaging-redis/
- https://www.baeldung.com/spring-data-redis-pub-sub