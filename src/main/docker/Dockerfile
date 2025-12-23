FROM eclipse-temurin:21-jre-alpine

# Для отладки (опционально)
RUN apk add --no-cache curl

# Копируем JAR
COPY target/*.jar app.jar

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "/app.jar"]