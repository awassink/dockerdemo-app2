FROM dockerfile/java:oracle-java8
ENTRYPOINT ["java", "-jar", "/app2.jar"]
CMD []
ADD target/app2.jar /
ENV spring.profiles.active docker
EXPOSE 8081