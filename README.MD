PARA GERAR UMA NOVA IMAGEM DOCKER 

1 PASSO

docker container run -d -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes --network fm-network --name fm-mysql mysql:8.0

2 PASSO 

./mvnw package -Pdocker -DskipTests

3 SUBIR PARA O DOCKER HUB