docker run -itd --rm --name db2 --privileged=true -p 50000:50000 -e LICENSE=accept -e DB2INST1_PASSWORD=admin123 -e DBNAME=sutdb apankura/java-spring-gradle-one-db2-db2
sleep 100
docker run -itd --rm --name spring --privileged=true -p 48601:48601 -e LICENSE=accept -e DB2INST1_PASSWORD=admin123 -e DBNAME=sutdb apankura/java-spring-gradle-one-db2-db2
