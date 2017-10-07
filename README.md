# bidder

The following tools/frameworks have been used for this exercise:

* MySQL
* JPA
* Maven
* Spring Boot with embedded Tomcat


## To Run
* Checkout the code
* Start MYSQL server and run bidder/src/main/resources/db/database-mysql.sql
* Changle values in bidder/src/main/resources/application.properties according to needs



## To Do

* Currently all banners are fetched and loaded into database, and the subsequest request to /bidder are served with data from database
* Need to add caching
* Testing has to be improved
* Sleep and Eat :(
