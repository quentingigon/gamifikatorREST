#!/bin/bash

cp target/swagger-spring-1.0.0.jar docker/images/springboot/swagger-spring_jar.jar
cd docker
docker-compose up --build
