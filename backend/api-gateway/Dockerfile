FROM openjdk:17-oracle

 # Refer to Maven build -> finalName
ARG JAR_FILE=target/api-gateway-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR /app

# cp target/{jarfileName}.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]