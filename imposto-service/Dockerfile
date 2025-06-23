# imagem para o processo de build
FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /workspace/app

COPY imposto-service/mvnw .
COPY imposto-service/mvnw.cmd .
COPY imposto-service/.mvn .mvn
COPY imposto-service/pom.xml .

RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

# Copiar o código-fonte
COPY imposto-service/src src
COPY imposto-service/controller controller
COPY imposto-service/factories factories
COPY imposto-service/imposto imposto
COPY imposto-service/model model
COPY imposto-service/repository repository
COPY imposto-service/service service

# Compilar o projeto
RUN ./mvnw clean package -DskipTests

# imagem para execução
FROM eclipse-temurin:21-jre-alpine AS final

WORKDIR /workspace/app

COPY --from=build /workspace/app/target/*.jar app.jar

EXPOSE 8200

ENTRYPOINT ["java", "-jar", "app.jar"]
