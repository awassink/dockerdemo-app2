FROM dockerfile/java:oracle-java8
ENTRYPOINT ["java", "-jar", "/app2.jar"]
EXPOSE 8081
ADD target/app2.jar /
