## SocoSolutions Project ##

========= 
Build and Deploy the Project =========
```
mvn clean install
```

=========
Deploy with Application.java =========

=========
MySQL init script =========
```
mysql -u root -p 
> CREATE USER 'user'@'localhost' IDENTIFIED BY 'Admin';
> GRANT ALL PRIVILEGES ON *.* TO 'admin'@'localhost';
> FLUSH PRIVILEGES;
```

========= 
Smtp =========
application.properties houses smtp credentials, need to mve out of hardcoding in propertied file
