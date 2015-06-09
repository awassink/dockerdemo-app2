FROM java:openjdk-8-jdk
ENTRYPOINT ["java", "-jar", "/app2.jar"]
EXPOSE 8081
ADD target/app2.jar /
