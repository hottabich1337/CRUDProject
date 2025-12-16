# Используем OpenJDK 21
FROM openjdk:21-jre-slim

# Копируем JAR-файл
COPY target/*.jar app.jar

# Указываем переменные окружения по умолчанию
ENV SPRING_PROFILES_ACTIVE=docker

# Запуск приложения
ENTRYPOINT ["java", "-jar", "/app.jar"]