docker-compose build
docker tag sut/spring-session localhost:32000/sut/spring-session
docker push localhost:32000/sut/spring-session