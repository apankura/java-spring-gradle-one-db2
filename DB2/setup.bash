docker stop registry
sleep 10
docker rm registry
sleep 10
docker container prune -f
sleep 10
docker image prune -a -f
sleep 10
docker run -d -p 48700:5000 --restart=always --name registry registry:latest
