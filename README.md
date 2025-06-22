sistvendas:
docker build -t sistvendas:latest -f sistvendas/Dockerfile sistvendas/
docker run -p 8100:8100 sistvendas:latest

naming-server:
docker build -t naming-server:latest -f naming-server/Dockerfile .
docker run -p 8761:8761 naming-server:latest

apigateway:
docker build -t api-gateway:latest -f api-gateway/Dockerfile .
docker run -p 8765:8765 api-gateway:latest

history-service:
docker build -t history-service:latest -f history-service/Dockerfile .
docker run -p 8120:8120 history-service:latest

imposto-service:
docker build -f imposto-service/Dockerfile -t imposto-service:latest .
docker run -p 8081:8080 imposto-service:latest

compose.yaml
docker-compose -f compose.yaml up --build
