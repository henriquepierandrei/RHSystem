# Human Resources Management System
An API created for people responsible for HR to manage and administer employees!

## Characteristics
● Employees Register and log into the system.

● Administrator manage absences, vacations and licenses.

● Get payroll with report on bonuses, discounts, absences, total amount, etc.

● Administrators Create, remove and update employee data.

● Employee logged into the system will be able to see the history of all their actions involving HR.

● Notifications involving the email registered by the employee regarding any matter involving them.

Installation
------------
To install this project and download dependencies, run these commands

```
git clone https://github.com/henriquepierandrei/RHSystem
cd RHSystem
mvn install
```

Settings
------------
You need to use PostgresQL to run this application and follow the settings below!
```
spring.application.name=RHSystem
spring.datasource.url=jdbc:postgresql://localhost:5432/rh_system_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
api.security.token.secret=my-secret-key
```

