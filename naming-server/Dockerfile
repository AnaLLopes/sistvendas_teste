# imagem para o processo de build
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /workspace/app

# copiar arquivos do maven da pasta naming-server
COPY naming-server/mvnw .
COPY naming-server/.mvn .mvn
COPY naming-server/pom.xml .

# baixar dependências offline
RUN ./mvnw dependency:go-offline

# copiar arquivos-fonte da pasta naming-server
COPY naming-server/src src

# build sem executar testes
RUN ./mvnw clean install -DskipTests

# imagem para produção
FROM eclipse-temurin:21-jre-alpine AS final
WORKDIR /workspace/app

# copiar o jar construído
COPY --from=build /workspace/app/target/*.jar /workspace/app/app.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "/workspace/app/app.jar"]
