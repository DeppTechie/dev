#1--This is automation suite is designed to test implementation


Given ​I like to holiday in Sydney
And ​I only like to holiday on Thursdays  
When ​I look up the weather forecast
Then ​I receive the weather forecast
And ​the temperature is warmer than 10 degrees

Assertions
The response is valid JSON and contains Sydney as a destination day of the weather forecast is
Thursday weather returned is > 10 degrees C

#2--The project has Only one scenario 
 @   src/test/java/feature/Holiday.feature

#3--You will need:
    - Java 1.8+ installed (Does not work with Java below 1.8) [I ran it on JDK 11 as well]
    - Maven Installed (I use version 3.6.1 
    - IntelliJ (Or another Java IDE)
    
 **Important: This suite should work on both windows and mac platforms however has only been tested on a Mac. If possible please use a Mac to execute the test suite
 
#4-- Run method
In order to execute the automation suite navigate to the Project directory within a Terminal/CMD window and run the command: 'mvn clean test'

#6-- Default Cucumber reports generated
@    target/cucumber-reports/index.html

#5 -- Below improvisation can be implemented 
    - Logger instead of SystemOutPrintLn
    - Data read from config/resoource file  
    - Fancier Reporting method
    
