=========
SocoSolutions Project
### Build and Deploy the Project
```
mvn clean install
```

## Deploy it by using Application.java

### Set up MySQL
```
mysql -u root -p 
> CREATE USER 'tutorialuser'@'localhost' IDENTIFIED BY 'Admin';
> GRANT ALL PRIVILEGES ON *.* TO 'admin'@'localhost';
> FLUSH PRIVILEGES;
```

### Smtp
application.properties houses smtp credentials, need to mve out of hardcoding in propertied file
