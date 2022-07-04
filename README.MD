This is the Ticket Tracker Service. To deploy this service the following needs to be done. 

1. mvn clean package to create a fat jar file. 
2. Move Jar file to artifact folder on droplet
3. Run sudo systemctl start hello-world-spring-boot.service to start up service.
4. journalctl --since "2015-01-10 17:15:00" to get all times since a certain time (this will give you the generated password)

