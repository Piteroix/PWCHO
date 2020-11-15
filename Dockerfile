FROM openjdk:8
ADD pawlak-java.jar pawlak-java.jar
EXPOSE 8086
ENTRYPOINT ["java","-jar","pawlak-java.jar"]