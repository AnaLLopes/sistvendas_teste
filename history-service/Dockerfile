# imagem para o processo de build
FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /workspace/app

# copiar arquivos do Maven
COPY history-service/mvnw .
RUN chmod +x mvnw
COPY history-service/.mvn .mvn
COPY history-service/pom.xml .

# baixar dependências
RUN ./mvnw dependency:go-offline

# copiar os arquivos-fonte
COPY history-service/src src

# build da aplicação
RUN ./mvnw clean install -DskipTests

# imagem para execução
FROM eclipse-temurin:21-jre-alpine AS final

WORKDIR /workspace/app

COPY --from=build /workspace/app/target/*.jar app.jar

EXPOSE 8120

ENTRYPOINT ["java", "-jar", "/workspace/app/app.jar"]
