# CustomerPhoneAPI
This is a github repository for a  REST API developed using Spring boot.

Build Restful API for retrieving and activating customer related phone numbers using Spring Boot.

# Requirements
Java - 17.x.x

Maven - 3.x.x

# Steps to Setup
1. Clone the application

git clone https://github.com/akarp31/CustomerPhoneAPI.git

2. Build and run the app using maven

mvn package
java -jar target/easy-notes-1.0.0.jar
Alternatively, you can run the app without packaging it using -

mvn spring-boot:run
The app will start running at http://localhost:8080.

Explore Rest APIs
The app defines following APIs.

GET /api/v1/phone-numbers

GET /api/v1/phone-numbers/{customerId}

PUT /api/v1/phone-numbers/{phoneNumber}
You can test them using postman or any other rest client.
