# Use uma imagem base do OpenJDK 17
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho no container
WORKDIR /app

# Copia o arquivo JAR gerado para o container
COPY build/libs/api-pessoa-0.0.1-SNAPSHOT.jar /app/api-pessoa.jar

# Expõe a porta geralmente 8080
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "api-pessoa.jar"]
