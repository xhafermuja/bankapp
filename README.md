The app is done with Java Spring Boot. 
So firstly you need to create a database called banksystem, and change your specifik configurations at application.properties, your root name password etc.
After you start the app via intelliJ. The tables of database are created after you start the app.
So when application is started, and xampp is started also. You can use Postman for all the Restful API.

For ex. when you create an account you need to post it via postman JSON raw data, also for creating a Bank in the same way.
For assing a bank to acc you need two parameters Integers @PutMapping("/{accountId}/bank/{bankId}") via the link.
So for deposit, withdraw, and transfer you need to do from Postman parameters.
