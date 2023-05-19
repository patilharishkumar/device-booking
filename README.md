##REFLECTION

What aspect of this exercise did you find most interesting?
Integration with Rapid api was interesting along i like to find tech info and bands its responding
like architecting this simple service API 

• What did you find most cumbersome?
I was not able to finish unit tesing part as have limited time in hand.
i could have done lot better error handling for application cumbersome part i think 
also finding alternative for fonoapi is cumbersome part but i have used rapid api in past to was known to this info.


Rapid APi is used to fetch device specs as fonoapi is not working

here is sample key to use : 7225d0c5a7msh350259fc2093b22p1ce3eajsn3d5a2c1ea0ad

Design diagram

![plot](./design-diagram1.png)

![plot](./spring-boot-architecture.png)


## Run the System
We can easily run the whole with only a single command:
```bash
docker-compose up
```

Docker will pull the MySQL and java images (if our machine does not have it before).

The services can be run on the background with command:
```bash
docker-compose up -d
```

## Stop the System
Stopping all the running containers is also simple with a single command:
```bash
docker-compose down
```

If you need to stop and remove all containers, networks, and all images used by any service in <em>docker-compose.yml</em> file, use the command:
```bash
docker-compose down --rmi all

```


## For testing of App console via curl or postman

Postman Collection  for Testing backend app
[link](./testing.postman_collection.json)

Sample data files with Valid and invalid files

Curl command for quick testing.

Create App with zip file

please provide path to test app.
e.g /Users/harishkumar.a.patil/Desktop/apptest2.zip
```bash
curl --location --request GET 'http://localhost:8080/api/v1/health'
```

Response
```bash
200 OK
The Phone Order Service is running!
 
400
not found
```

Get List of Devices 

```bash
curl --location --request GET 'http://localhost:8080/api/v1/devices'

[
    {
        "phoneId": 1,
        "phoneName": "Samsung Galaxy S9",
        "count": 1,
        "available": "Yes"
    },
    {
        "phoneId": 2,
        "phoneName": "Samsung Galaxy S8",
        "count": 2,
        "available": "Yes"
    },
    {
        "phoneId": 3,
        "phoneName": "Motorola Nexus 6",
        "count": 1,
        "available": "Yes"
    },
    {
        "phoneId": 4,
        "phoneName": "Oneplus 9",
        "count": 1,
        "available": "Yes"
    },
    {
        "phoneId": 5,
        "phoneName": "Huawei Honor 13",
        "count": 1,
        "available": "Yes"
    },
    {
        "phoneId": 6,
        "phoneName": "Apple iPhone 12",
        "count": 1,
        "available": "Yes"
    },
    {
        "phoneId": 7,
        "phoneName": "Apple iPhone 11",
        "count": 1,
        "available": "Yes"
    },
    {
        "phoneId": 8,
        "phoneName": "Apple iPhone x",
        "count": 1,
        "available": "Yes"
    },
    {
        "phoneId": 9,
        "phoneName": "Nokia 3310",
        "count": 1,
        "available": "Yes"
    }
]

```

Get device

```bash
curl --location --request GET 'http://localhost:8080/api/v1/device/Samsung%20Galaxy%20S9'

200 OK
{
    "phoneId": "1",
    "phoneName": "Samsung Galaxy S9",
    "technology": "GSM / CDMA / HSPA / EVDO / LTE",
    "network2GBands": "GSM 850 / 900 / 1800 / 1900 - SIM 1 & SIM 2 (dual-SIM model only)\\nCDMA 800 / 1900 - USA",
    "network3GBands": "HSDPA 850 / 900 / 1700(AWS) / 1900 / 2100 - Global, USA\\nCDMA2000 1xEV-DO - USA",
    "network4GBands": "1, 2, 3, 4, 5, 7, 8, 12, 13, 17, 18, 19, 20, 25, 26, 28, 32, 38, 39, 40, 41, 66 - Global\\n1, 2, 3, 4, 5, 7, 8, 12, 13, 14, 17, 18, 19, 20, 25, 26, 28, 29, 30, 38, 39, 40, 41, 46, 66, 71 - USA",
    "bookedBy": "N/A",
    "bookedOn": null,
    "isAvailable": "Yes"
}

400  Expectation Failed 
{
    "timestamp": "2023-05-18T10:40:09.968+00:00",
    "message": "Samsung Galaxy S10 No Device Found!",
    "details": "uri=/api/v1/device/Samsung%20Galaxy%20S10"
}
```
Book Device
```bash
curl --location 'localhost:8080/api/v1/book' \
--header 'Content-Type: application/json' \
--data '{"phoneName":"Samsung Galaxy S9","bookedBy":"Bob"}'
200 OK
{
    "phoneId": "1",
    "phoneName": "Samsung Galaxy S9",
    "bookedBy": "Bob",
    "bookedOn": "2023-05-18T19:47:55.493684",
    "isAvailable": "No",
    "status": "booked"
}


400 ERROR
{
    "timestamp": "2023-05-18T10:37:28.212+00:00",
    "message": "Samsung Galaxy S9 can't be booked again!",
    "details": "uri=/api/v1/book"
}
```

Return Device
```bash
curl --location --request POST 'http://localhost:8080/api/v1/return/Samsung Galaxy S9'

200 OK
{
    "phoneId": "1",
    "phoneName": "Samsung Galaxy S9",
    "returnedBy": "Bob",
    "returnedOn": "2023-05-18T17:55:32.068256",
    "isAvailable": "Yes",
    "status": "returned"
}
400
{
    "timestamp": "2023-05-18T10:37:43.338+00:00",
    "message": "Samsung Galaxy S9 was never booked, so it can't be returned!",
    "details": "uri=/api/v1/return/Samsung%20Galaxy%20S9"
}
404 Expectation Failed 
{
    "timestamp": "2023-05-18T10:11:37.249+00:00",
    "status": 404,
    "error": "Not Found",
    "message": "No message available",
    "path": "/api/v1/return/Samsung%20Galaxy%20S10"
}
```



