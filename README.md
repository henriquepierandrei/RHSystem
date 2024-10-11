# Human Resources Management System

An API created for HR professionals to manage and administer employees!

## Characteristics

- Employees can register and log into the system.
- Administrators can manage absences, vacations, and licenses.
- Payroll can be generated with detailed reports on bonuses, deductions, absences, total amounts, etc.
- Administrators can create, remove, and update employee data.
- Logged-in employees can view the history of all their HR-related actions.
- Notifications are sent via the employee's registered email regarding any matter concerning them.

## Installation

To install this project and download dependencies, run the following commands:

```bash
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


› EndPoints
------------
## 🔒 Authentication Controller

### ৹ Login:
- **Description**: Use JWT as the authenticator to log in.
- **Endpoint**: 

    ```
    [ POST ] http://localhost:8080/auth/login
    ```
- **Request Body**:
    ```json
    {
        "cpf": "111.111.111-11",
        "password": "password123"
    }
    ```
- **Response**: A JWT token for authentication.

---

### ৹ Register:
- **Description**: Register a new employee in the system.
- **Endpoint**: 

    ```
    [ POST ] http://localhost:8080/auth/register
    ```
- **Request Body**:
    ```json
    {
        "name": "John Doe",
        "email": "johndoe@example.com",
        "password": "password123",
        "dateBorn": "1111-11-11",
        "cpf": "111.111.111-11",
        "phone": "11111111111",
        "rg": "1.111.111-1"
    
    }
    ```

---
## 🪪 Administrator Controller

### ৹ Create employee contract:
- **Description**: Create employee contract in the system:
- Required ROLE_ADMIN
- **Endpoint**: 

    ```
    [ POST ] http://localhost:8080/admin/contract
    ```
- **Request Body**:
    ```json
    {
       {
          "startDate": "2024-10-10",
          "cpf": "111.111.111-11",
          "rg": "11.111.111-1",
          "typeContract": "CLT",
          "position": "Desenvolvedor",
          "wage": 5000.00,
          "shiftContract": "MEIO_PERIODO",
          "statusContract": "ATIVO",
          "bonus": 500.00
        }
    }
    ```
- **Response**:
```json
  {
          "startDate": "2024-10-10",
          "cpf": "111.111.111-11",
          "rg": "11.111.111-1",
          "typeContract": "CLT",
          "position": "Desenvolvedor",
          "wage": 5000.00,
          "shiftContract": "MEIO_PERIODO",
          "statusContract": "ATIVO",
          "bonus": 500.00
        }
```
---

### ৹ Delete Contract and Employee:
- **Description**: Create employee contract in the system:
- Required ROLE_ADMIN
- **Endpoint**: 

    ```
    [ DELETE ] http://localhost:8080/admin/delete/all
    ```
- **RequestParam**:
    ```
    cpf=111.111.111-11
    rg=11.111.111-1
    ```
- **Response**:

```200 OK - "Funcionário e contrato deletado!"```
  
```404 BAD REQUEST - "Erro ao remover o funcionário e contrato: ERRO"```

---

### ৹ Update Employee Contract:
- **Description**: Update the contact details (email and telephone) of the contract that were provided
- Required ROLE_ADMIN
- **Endpoint**: 

    ```
    [ PUT ] http://localhost:8080/admin/update/contact
    ```
- **RequestParam**:
    ```
    cpf=111.111.111-11
    rg=11.111.111-1
    email=xxxx@xxxx.com
    phone=11111111111111
    ```
- **Response**:

```200 OK - "Dados do funcionário atualizados!"```
  
```404 BAD REQUEST - "Erro ao remover o funcionário e contrato: ERRO"```

---



## 💼 Employee Controller
### ৹ Get contract information:
- **Description**: Returns the contract and its information
- **Endpoint**: 

    ```
    [ GET ] http://localhost:8080/employee/contract
    ```
- **Response**:
    ```json
    {
        {
    "employeeModel": {
        "id": 152,
        "name": "Fulano",
        "email": "Fulano@gmail.com",
        "dateBorn": "1990-05-15",
        "cpf": "111.111.111-11",
        "phone": "11987651345",
        "rg": "11.111-111.1",
        "password": "$2a$10$X.6khMapRny4hsdl31cyAe7n21zOahNIxzj6VVNoAVSpB8dtnmCGG",
        "admin": true
    },
    "startDate": "2024-10-10",
    "endDate": null,
    "typeContract": "CLT",
    "position": "Desenvolvedor",
    "wage": 5000.0,
    "shift": "MEIO_PERIODO",
    "statusContract": "ATIVO",
    "bonus": 500.0,
    "absentValue": 250.0
    
    }
    ```

---




