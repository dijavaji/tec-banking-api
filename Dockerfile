# Etapa de construccion
FROM maven:latest AS build

WORKDIR /app

# Copiar el archivo pom.xml y el directorio src para la construcción
COPY pom.xml .
COPY src ./src

# Construir la aplicación
RUN mvn clean package -DskipTests

# Etapa de ejecucion
#define base docker image
FROM amazoncorretto:21-alpine-jdk

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el JAR construido desde la etapa de construcción
COPY --from=build /app/target/bankingapp-0.0.1-SNAPSHOT.jar .
#COPY target/*.jar /app/application.jar

EXPOSE 3000

LABEL maintainer="technoloqie.com.ec"

ENTRYPOINT ["java", "-jar", "bankingapp-0.0.1-SNAPSHOT.jar"]
