# Usa una imagen base de OpenJDK con Alpine Linux para un tamaño reducido
FROM openjdk:17-jdk-slim-buster

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR de tu aplicación al contenedor
# Asume que ya has construido tu proyecto Maven con 'mvn clean package'
# Reemplaza 'franchise-api-0.0.1-SNAPSHOT.jar' con el nombre de tu JAR generado
COPY target/franchise-api-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que la aplicación Spring Boot se ejecutará (por defecto 8080)
EXPOSE 8080

# Comando para ejecutar la aplicación JAR cuando el contenedor se inicie
ENTRYPOINT ["java","-jar","app.jar"]

# Argumentos del comando de ejecución (opcional, para pasar variables de entorno de Spring)
# Puedes añadir aquí opciones de JVM si lo necesitas, por ejemplo:
# ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]