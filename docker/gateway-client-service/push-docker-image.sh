export DOCKER_ID_USER=accurateriopreto
docker login
docker tag $1:latest $DOCKER_ID_USER/$1:$2
docker push $DOCKER_ID_USER/$1:$2

