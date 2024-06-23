[Backend Application Link](https://mesonsprojecttester.ca-central-1.elasticbeanstalk.com/)

# Introduction
This is a backend for RESTful API to have client connect to DynamoDB.

Using this application along with the frontend, clients are able to make CRUD operations in user-friendly manner.

## Geting Started
Pre-requisite:
1. Creating IAM user for DynamoDB
2. Creating DynamoDB Table
3. Installation of [Java 17 JDK](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
4. Spring-boot and Apache Maven

### Creating IAM
1. Go to AWS Console
2. Search for IAM
3. On the left panel, go to "Users"
4. Click on Create User
5. Name the user i.e: DynamoDBUserAccess
6. Click "Next"
7. Click "Create Group"
8. Create a name for user group i.e: DynamoDBFullAccess
9. Search for "DynamoDB"
10. Click on "AmazonDynamoDBFullAccess"
11. Click "Create user group" WARNING: best practice for security is to have principle of least privilege. Use it at your own risk.
12. Check recently created Group name
13. Click "Create User"
14. Go to the recently created user. i.e: DynamoDBUserAccess
15. Click "Create access key"
16. Choose Other
17. Click "Create access key"
18. Copy the access key and secret access key

### Creating DynamoDB
1. Go to AWS Console
2. Search DynamoDB
3. Go to "Tables" on the left panel
4. Click "Create table"
5. Set the table name as "MesonsTechInterviewProjectApplication" (requirement)
6. Set Partition key as "Id" (requirement)
7. Click "Create Table"

### Adding keys to application.properties
1. After Downloading the code and following the previous steps open application.properties
2. in
```bash
amazon.aws.accesskey=<your accesskey>
amazon.aws.secretkey=<your secretkey>
```
Replace <your accesskey> with the access key that you created
Replace <your secretkey> with the secret access key that you created

### Running the application
1. At the root of the project, run:
```bash
mvn spring-boot:run
```
2. Go to localhost:5000
