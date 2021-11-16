
# Setup Instruction


### Run database using docker-compose: 

$ docker-compose up -d


### Run web application using mvn:

$ mvn clean compile exec:java


### Using the API:


#### Add new URL:

$ curl -v -X POST -H 'Content-type: application/json' --data '{"name":"Gmail","url":"http://gmail.com"}'  http://localhost:8080/service/url


#### Update URL:

$ curl -v -X PUT -H 'Content-type: application/json' --data '{"name":"Gmail","url":"http://gmail.com"}'  http://localhost:8080/service/url/<SERVICE_URL_ID>



#### Get an URL:

$ curl -v http://localhost:8080/service/url/<SERVICE_URL_ID>



#### Get list of URL:

$ curl -v http://localhost:8080/service/url



