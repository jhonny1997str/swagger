# Etapa de construcción
#compila y genera el JAR Un JAR (Java ARchive) es un formato de archivo utilizado principalmente para empaquetar
 #múltiples archivos Java en un solo archivo comprimido.
FROM maven:3.9.6-eclipse-temurin-22-jammy AS build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo pom.xml en el directorio de trabajo del contenedor
COPY pom.xml .

# Copia el directorio 'src' en el contenedor, que contiene el código fuente de la aplicación
COPY src ./src

# Ejecuta el comando Maven para limpiar y empaquetar la aplicación
# - clean: elimina los archivos generados en compilaciones anteriores
# - package: compila el código y empaqueta la aplicación en un archivo JAR
# -DskipTests: omite la ejecución de pruebas durante el empaquetado
RUN mvn clean package -DskipTests

# Etapa de ejecución
# Utiliza una imagen base de OpenJDK 21 en una versión ligera
FROM openjdk:21-jdk-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado la etapa de construcción y lo renombre app.jar
# 'swagger-0.0.1-SNAPSHOT.jar' es el nombre del archivo JAR generado por Maven
COPY --from=build /app/target/swagger-0.0.1-SNAPSHOT.jar app.jar

# Comando de entrada que se ejecutará cuando se inicie el contenedor
#significa que el contenedor ejecutará la aplicación Java que está empaquetada dentro del archivo app.jar.
ENTRYPOINT ["java", "-jar", "app.jar"]



