#!/bin/bash

cp target/swagger-spring-1.0.0.jar docker/images/springboot/swagger-spring_jar.jar
cp db/script_db.sql docker/images/mysql/src/script_db.sql 
cd docker
docker-compose build --no-cache
docker-compose up
cd ..
