# SkyNet
Project 5 of OC Java courses. 

It is an app working without a database as requested. It creates a bean of the data created by a Json reader.
T
he Json Reader read the data on the file in resources data.json

------------------------------HOW TO  INSTALL----------------------------------------------

To install Maven : 
https://maven.apache.org/install.html
To install Java : 
https://www.java.com/en/download/help/download_options.html

---------------------------- HOW TO START THE APP -----------------------------------------

Write the following command in the Terminal : 
java –jar <ExecutableJarFileName>.jar.

------------------------------- HOW IT WORKS ----------------------------------------------

It works at the URL : localhost:8080/

1. TO MODIFY THE DATA ---------------------------------------------------------------------

http://localhost:8080/person 

PUT POST DELETE http method with a Json person in the body. 

PUT : The first name and the last name doesn't change.


http://localhost:8080/firestation

PUT POST DELETE http method with a Json firestation in the body.

PUT: Only update the firestation number of an address.


http://localhost:8080/medicalRecord

PUT POST DELETE http method with a Json medical record in the body 

PUT : The first name and the last name doesn't change.


2. TO GET THE DATA. -----------------------------------------------------------------------

http://localhost:8080/firestation?stationNumber=<station_number>

This url returns a list of persons covered by the FireStation of the station number. The list include : first name, last name, address, phone number, the number of minor and the number of adults.


http://localhost:8080/childAlert?address=<address>

this url returns a list of child (person age <18 ) leaving at the address. the list contains first name, last name, age, and a list of the person leaving at the same home.


http://localhost:8080/phoneAlert?firestation=<firestation_number>

this url returns a list of the phone number of the resident covered by the fire station of the station number. It is used to give emergency messages to a specific home.


http://localhost:8080/fire?address=<address>

this url returns a list of resident leaving at the address and the number of the Fire Station covering it. The list include first name, last name, phone number, age, the list of medications and the list of allergies of each person.


http://localhost:8080/flood/stations?stations=<a list of station_numbers>

This url returns a list of all the homes covered by the Fire Station numbers. This list contains first name, last name, phone number, age, the list of medications and the list of allergies of each person.


http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>

This url returns the person information. The person information contains first name, last name, age, email address, the list of medications and the list of allergies.


http://localhost:8080/communityEmail?city=<city>

This url returns all the email addresses of all the residents in the city.
