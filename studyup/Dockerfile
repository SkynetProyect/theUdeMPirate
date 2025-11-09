# Etapa 1: compilar el proyecto
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

COPY . .
RUN ./gradlew clean build -x test

#Etapa 2: usar
FROM eclipse-temurin:21-jre AS use
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080

# Comando de inicio
ENTRYPOINT ["java","-jar","app.jar"]
