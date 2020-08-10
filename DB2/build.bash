pushd "./containers"

docker-compose build

popd

docker tag sut/db2 localhost:32000/sut/db2
docker push localhost:32000/sut/db2
