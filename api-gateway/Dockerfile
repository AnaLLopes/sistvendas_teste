# imagem para o processo de build
FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /workspace/app

# copiar arquivos do maven
COPY mvnw .
COPY .mvn .mvn

# copiar o arquivo com as dependências
COPY pom.xml .

RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

# copiar os arquivos-fonte
COPY src src

# realizar o build da aplicação sem executar os testes
RUN ./mvnw clean install -DskipTests

# imagem para execução
FROM eclipse-temurin:21-jre-alpine AS final

WORKDIR /workspace/app

COPY --from=build /workspace/app/target/*.jar app.jar

EXPOSE 8765
ENTRYPOINT ["java", "-jar", "/workspace/app/app.jar"]
