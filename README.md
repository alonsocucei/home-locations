# home-locations
<pre>
This is an example that uses dropwizard to create a basic REST api for home locations and plot them in a google map.

You willl need to have installed:
Maven
MySql
   User and pwd are configured in config.xml

To start the app just execute these commands:

Windows:
build.bat
startApp.bat

You'll be prompted for the database password on each command.

If you're using a different OS you'll need to execute these commands or its equivalent:

mysql -uroot -p < homes.sql (This will create the database and will insert 5 records, this is needed in order tests to run without errors)
mvn clean package (It will compile the code, execute tests and genereate the jar file of the app)
mysql -uroot -p < homes.sql (Again, this is just to remove those records added by tests)
java -jar target\home-locations-1.0-SNAPSHOT.jar server config.yml (It will run the app)

Finally to see the application running open a web browser at http://localhost:8080
</pre>
