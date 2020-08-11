docker-compose build
docker tag sut/spring-session localhost:5000/sut/spring-session
docker push localhost:5000/sut/spring-session
