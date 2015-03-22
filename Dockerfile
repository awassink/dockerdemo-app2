FROM dockerfile/java:oracle-java8
ENTRYPOINT ["java", "-jar", "/app2-1.0-SNAPSHOT.jar"]
CMD []
ADD target/app2-1.0-SNAPSHOT.jar /app2-1.0-SNAPSHOT.jar
ENV spring.profiles.active docker
EXPOSE 8081