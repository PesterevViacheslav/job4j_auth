# job4j_auth
Приложение "Job4j Rest API".

## Описание проекта
- Цель - Изучение архитектуры. RestFul API архитектура - это архитектура клиент-серверного приложения, базирующаяся на 6 принципах.

1. Универсальный интерфейс взаимодействия. (Uniform Interface)

2. Запросы без состояния. (Stateless)

3. Поддержка кеширования. (Cacheable)

4. Клиент-серверная ориентация.

5. Поддержка слоев (Layered System)

6. Возможность выполнять код на стороне клиента (Code on Demand)

## Стек технологий
- Java 17
- Hibernate 5.5.3
- PostgreSQL 14
- Maven 3.8
- Spring boot 2.7
- Bootstrap 4.4

## Требования к окружению
- JDK 17
- Maven
- PostgreSQL

## Запуск проекта
- ```git clone git@github.com/PesterevViacheslav/job4j_auth.git```
- Postgres. ```create database auth;```
- Прописать креды в ```src/main/resources/db.properties```
- ```mvn install```
- перейти по [http://localhost:8080/auth]

## Взаимодействие с приложением