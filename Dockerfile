dFROM eclipse-temurin:17.0.3_7-jre-alpine
WORKDIR /
ADD "/target/*.jar" "web-app.jar"
CMD ["sh", "-c", "java -jar $JAVA_OPTS ./web-app.jar"]