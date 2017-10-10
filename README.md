# bidder

The following tools/frameworks have been used for this exercise:

* MySQL
* JPA
* Maven
* Spring Boot with embedded Tomcat


## To Run
* Checkout the code
* Start MYSQL server and run bidder/db/mysql.sql
* Change values in banner-consumer/src/main/resources/application.properties according to needs
* Change values in bidder-service/src/main/resources/application.properties according to needs
* Build bidder-service and banner-consumer using maven
* Run the executable jar , banner-consumer/api.jar
* 



## To Do

* Currently all banners are fetched and loaded into database, and the subsequest request to /bidder are served with data from database
* Need to add caching
* Testing has to be improved
* Sleep and Eat :(
