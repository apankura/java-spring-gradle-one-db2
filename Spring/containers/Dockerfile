FROM gradle:jdk8 as builder
    WORKDIR /build/
    COPY ./ .    
    RUN  sed -i -e 's/localhost:50000/service-db2-session.sut.svc.cluster.local:48100/g' ./src/main/resources/application.properties
    RUN whoami
    RUN chmod -R 777 /build/
    RUN gradle wrapper
    RUN ./gradlew build
    RUN ls -a /build/build/libs/


FROM openjdk:8-jdk-alpine
    COPY --from=builder /build/build/libs/ /app
    WORKDIR /app
    ENTRYPOINT ["java","-jar","demo-0.0.1.jar"]


