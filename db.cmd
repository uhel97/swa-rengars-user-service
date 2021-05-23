echo "Running MySql..."
docker run --name mysql --network dev-network -v mysql_data:/var/lib/mysql -p 3306:3306 -d -e MYSQL_ROOT_PASSWORD=root mysql:8.0