# === 1. Билд-этап: собираем JAR с помощью Maven ===
FROM eclipse-temurin:21-jdk AS builder

# Рабочая директория внутри контейнера
WORKDIR /app

# Копируем pom.xml и исходники
COPY pom.xml .
COPY src ./src

# Устанавливаем Maven и собираем приложение
RUN apt-get update && apt-get install -y maven && \
    mvn -B -q clean package -DskipTests

# === 2. Рантайм-этап: запускаем собранный JAR ===
FROM eclipse-temurin:21-jre

WORKDIR /app

# Копируем только готовый JAR из первого этапа
COPY --from=builder /app/target/*.jar app.jar

# Указываем порт (должен совпадать с server.port в application.properties)
EXPOSE 8081


# Запуск приложения
ENTRYPOINT ["java", "-jar", "app.jar"]