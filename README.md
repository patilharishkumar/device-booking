##REFLECTION
What aspect of this exercise did you find most interesting?
• What did you find most cumbersome?



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
[link](./AppConsole.postman_collection.json)

Sample data files with Valid and invalid files
Invalid App file -> apptest.zip  [link](./apptest.zip)

valid App file -> apptest.zip  [link](./apptest2.zip)

Curl command for quick testing.

Create App with zip file

please provide path to test app.
e.g /Users/harishkumar.a.patil/Desktop/apptest2.zip
```bash
curl --location --request POST 'http://localhost:6868/apps' \
--header 'Content-Type: multipart/form-data' \
--form 'developerId="2esdadasdasdasd222"' \
--form 'appName="testappsss"' \
--form 'file=@"/Users/harishkumar.a.patil/Desktop/apptest2.zip"'
```

Response
```bash
200 OK
{
    "message": "App Uploaded after review successfully: apptest2.zip",
    "appName": "testappsss",
    "id": "ee436660-daee-4713-a0bd-585f9939af5c",
    "developerId": "2esdadasdasdasd222",
    "downloadURL": "http://localhost:6868/files/ac0b26e6-89c1-415d-9ee5-c2b195ad9254",
    "appStatus": "APPROVED"
}


If file dose Not match criteria 
417 Expectation Failed 

{
    "message": "App review failed app should only contains .doesntmakesense extension files : apptest.zip",
    "appName": "testappsss",
    "id": null,
    "developerId": "2esdadasdasdasd222",
    "downloadURL": null,
    "appStatus": "REJECTED"
}
```

Get List of Apps 

```bash
curl --location --request GET 'http://localhost:6868/apps'

[
     {
        "id": "2e7174e9-d9cf-4bbb-9432-eec72505d4a6",
        "developerId": "2esdadasdasdasd222",
        "appName": "testappsss",
        "appStatus": "REJECTED",
        "downloadURL": ""
    },
    {
        "id": "4c7b45a7-6f4e-4cd9-8ca6-6e6bc021c3b5",
        "developerId": "2esdadasdasdasd",
        "appName": "testapp1",
        "appStatus": "APPROVED",
        "downloadURL": "http://localhost:6868/files/f1ac93e6-a0de-4b23-94e1-7d70a133a095"
    },
    {
        "id": "61e357ec-2348-41e8-ae2a-3dd9cf808b89",
        "developerId": "2esdadasdasdasd",
        "appName": "testapp2",
        "appStatus": "NOT_UPLOADED",
        "downloadURL": ""
    },
    {
        "id": "8e6d35f7-d862-4623-85c5-df2f0d74b6b4",
        "developerId": "2esdadasdasdasd",
        "appName": "testapp3",
        "appStatus": "APPROVED",
        "downloadURL": "http://localhost:6868/files/64f03b38-d4d8-4aeb-bba1-37d311cb469d"
    }
]

```

Get App

```bash
curl --location --request GET 'http://localhost:6868/apps/ee436660-daee-4713-a0bd-585f9939af5c'

200 OK
{
    "id": "ee436660-daee-4713-a0bd-585f9939af5c",
    "developerId": "2esdadasdasdasd222",
    "appName": "testappsss",
    "appStatus": "APPROVED",
    "downloadURL": "http://localhost:6868/files/ac0b26e6-89c1-415d-9ee5-c2b195ad9254"
}

417 Expectation Failed 
{
    "message": "No value present",
    "appName": null,
    "id": null,
    "developerId": null,
    "downloadURL": null,
    "appStatus": null
}
```


Delete App
```bash
curl --location --request DELETE 'http://localhost:6868/apps/ee436660-daee-4713-a0bd-585f9939af5c'

200 OK
{
    "id": "ee436660-daee-4713-a0bd-585f9939af5c",
    "developerId": "2esdadasdasdasd222",
    "appName": "testappsss",
    "appStatus": "DELETED",
    "downloadURL": ""
}

417 Expectation Failed 
{
    "message": "App not found",
    "appName": null,
    "id": null,
    "developerId": null,
    "downloadURL": null,
    "appStatus": null
}
```

Download App File
```bash
curl --location --request GET 'http://localhost:6868/files/58e2c9fc-3117-4881-ae06-858e16b20063'

200 OK 
with file content 
```


## Swagger Markdown
Read for More details here [link](./appconsole.md)

![plot](./APIs.png)

Apps

![plot](./swagger-app.png)

![plot](./appUpload.png)


REFLECTION:
• What aspect of this exercise did you find most interesting?
• What did you find most cumbersome?



