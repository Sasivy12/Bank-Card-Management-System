# Bank Card Management System

Это Spring Boot-приложение для управления банковскими картами, пользователями и транзакциями. Поддерживает роли (ADMIN / USER), аутентификацию и авторизацию, регистрацию, вход, транзакции, просмотр и управление картами.

## Технологии

- Java 21
- Spring Boot
- Spring Security (JWT)
- PostgreSQL
- Docker & Docker Compose
- Maven
- Swagger (SpringDoc OpenAPI)
- JUnit + Mockito

---

## Быстрый старт

### 🔧 Требования

- Java 21+
- Maven 3.8+
- Docker и Docker Compose

---

### Запуск через Docker Compose

1. Сборка  JAR-файла:
   ```bash
   mvn clean package
2. Запуск контейнеров
   ```bash
   docker-compose up --build
3. Приложение будет доступно на
   ```bash
   http://localhost:8081
4. Swagger UI:
```bash
  http://localhost:8081/swagger-ui/index.html
