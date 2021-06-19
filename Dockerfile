FROM openjdk:8
ADD target/pc-spring-boot-products-jpa.jar pc-spring-boot-products-jpa.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","pc-spring-boot-products-jpa.jar"]