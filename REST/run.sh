#!/bin/bash

#cp target/swagger-spring-1.0.0.jar docker/images/springboot/swagger-spring_jar.jar
#cp db/script_db.sql docker/images/mysql/src/script_db.sql 

docker build docker/images/mysql/ --no-cache                                   
docker build docker/images/springboot --build-arg JAR_FILE="swagger-spring_jar.jar" -t="rest_server" --no-cache

docker run --network="host" -h 127.0.0.1 docker_db &
docker run --network="host" rest_server &

echo "APPLICATION AND DATABASE STARTED."
echo "go to http://localhost:8081/v1/ and start poking ;)"
