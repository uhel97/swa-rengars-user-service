echo "Building microservice user-service..."
mvn install -DskipTests
echo "Dockerizing microservice user-service..."
docker rm user-service
docker build -t user-service .
echo "Running container user-service..."
docker run --name user-service --network dev-network -p 8090:8090 user-service
