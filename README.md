# Offer Management Service

[Intro]

This is a simple offer management service baesd on Java Restful APIs which uses spring boot, h2 in-memory database

Feature list:

1. create offer

2. update offer, e.g. price, quantity, currency, status and so on

3. query offer

4. delete offer

** each offer has a due date and it can be updated(status change, e.g. Delivered, Cancelled) before it expired

[Usage]

1. build and run the project using Maven version >= 3.5.0

command: mvn spring-boot:run

2. run test cases

command: mvn test

3. the server runs at port 8080, make sure it's available to use

4. h2 in memory databse console ui

navigate to http://localhost:8080/h2-console to the console ui

loging info:

jdbc url: jdbc:h2:mem:testdb

user name: admin

password: 123456

** All tables will be created automatically because of the following configuration

spring.jpa.hibernate.ddl-auto = update

[API enpoints]

**postman is a helpful tool to test APIs

1. Create offer

[POST] http://localhost:8080/api/offers

param: json data

{ "customerId": 1, 
  "quantity": 23,
  "price": 12500,
  "currency": "GBP",
  "offerStatus": "Created",
  "dueDate": "2018-05-01",
  "remark": "test" }

response:

{
    "createdTime": "2018-06-30T22:01:21.287+0000",
    "lastModified": "2018-06-30T22:01:21.287+0000",
    "id": 1,
    "quantity": 23,
    "price": 12500,
    "currency": "GBP",
    "offerStatus": "Created",
    "dueDate": "2018-05-01T00:00:00.000+0000",
    "remark": "test"
}

2. Get all offers

[GET] http://localhost:8080/api/offers

param: none

response: 

[
    {
        "createdTime": "2018-06-30T22:01:21.287+0000",
        "lastModified": "2018-06-30T22:01:21.287+0000",
        "id": 1,
        "quantity": 23,
        "price": 12500,
        "currency": "GBP",
        "offerStatus": "Created",
        "dueDate": "2018-05-01T00:00:00.000+0000",
        "remark": "test"
    },
    {
        "createdTime": "2018-06-30T22:02:24.126+0000",
        "lastModified": "2018-06-30T22:02:24.126+0000",
        "id": 2,
        "quantity": 23,
        "price": 12500,
        "currency": "GBP",
        "offerStatus": "Created",
        "dueDate": "2018-05-01T00:00:00.000+0000",
        "remark": "test"
    }
]

3. Get offer by id

[GET] http://localhost:8080/api/offers/{id}

e.g. http://localhost:8080/api/offers/3

param: offer id in uery string

reponse:

{
    "createdTime": "2018-06-30T22:01:21.287+0000",
    "lastModified": "2018-06-30T22:01:21.287+0000",
    "id": 1,
    "quantity": 23,
    "price": 12500,
    "currency": "GBP",
    "offerStatus": "Created",
    "dueDate": "2018-05-01T00:00:00.000+0000",
    "remark": "test"
}

4. Update offer

[PUT] http://localhost:8080/api/offers/{id}

e.g. http://localhost:8080/api/offers/3

param: offer id in uery string, and following json data

{"customerId": 1, "quantity": 125, "price": 3500, "currency": "JPY", "offerStatus": "Cancelled", "remark": "Cancelled"}

response:

{
    "createdTime": "2018-06-30T22:07:11.426+0000",
    "lastModified": "2018-06-30T22:07:47.027+0000",
    "id": 3,
    "quantity": 125,
    "price": 3500,
    "currency": "JPY",
    "offerStatus": "Cancelled",
    "dueDate": "2018-08-01T00:00:00.000+0000",
    "remark": "Cancelled"
}

error response:

Offer expired:

e.g.

{
    "timestamp": "2018-06-30T22:09:40.190+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Offer expired with id : '2'",
    "path": "/api/offers/2"
}

5. Delete offer

[DELETE] http://localhost:8080/api/offers/{id}

e.g. http://localhost:8080/api/offers/3

param: offer id in uery string

response: none, http status 200

thanks for reading,

jeff
