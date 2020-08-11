FROM gradle:jdk8 as builder
    WORKDIR /build/
    COPY ./ .    
    RUN whoami
    RUN chmod -R 777 /build/
    RUN gradle wrapper
    RUN ./gradlew build
    RUN ls -a /build/build/libs/


FROM openjdk:8-jdk-alpine
    COPY --from=builder /build/build/libs/ /app
    WORKDIR /app
    ENTRYPOINT ["java","-jar","demo-0.0.1.jar"]


