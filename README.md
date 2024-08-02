# Account Service

Service used to manage users. Account Service focuses on authentication and authorization.

TODO:

### Endpoints

    Service ADMIN functionality
    (TODO) PUT api/admin/user/role changes user roles; (in progress)
    (TODO) DELETE api/admin/user deletes a user;
    (TODO) GET api/admin/user displays information about all users.

    
    Authentication
    POST api/auth/signup allows the user to register on the service;
    (TODO) POST api/auth/changepass changes a user password.


    Accountancy functionality
    (In prog) GET api/empl/payment gives access to the employee's payrolls;
    (TODO) POST api/acct/payments uploads payrolls;
    (TODO) PUT api/acct/payments updates payment information.

---

### API access authority and roles permissions

| Endpoint                 | Anonymous | User | Accountant | Administrator |
|--------------------------|-----------|------|------------|---------------|
| POST api/auth/signup     | +         | +    | +          | +             |
| POST api/auth/changepass | -         | +    | +          | +             |
| GET api/admin/user       | -         | -    | -          | +             |
| DELETE api/admin/user    | -         | -    | -          | +             |
| PUT api/admin/user/role  | -         | -    | -          | +             |

### Employee management functionalities

| Endpoint               | Anonymous | User | Accountant | Administrator |
|------------------------|-----------|------|------------|---------------|
| GET api/empl/payment   | -         | +    | +          | -             |
| POST api/acct/payments | -         | -    | +          | -             |
| PUT api/acct/payments  | -         | -    | +          | -             |

---

## Authentatication

POST /api/auth/signup

### Endpoint Descriptions

The `POST /api/auth/signup` endpoint allows a user to register by providing the following JSON payload:

```json
{
  "name": "<String value, not empty>",
  "lastname": "<String value, not empty>",
  "email": "<String value, not empty>",
  "password": "<String value, not empty>"
}
```

#### Example Response

Response 200 OK

```json
{
  "id": "<user Identifer>",
  "name": "<name>",
  "lastname": "<lastname",
  "email": "<uer>@beije.com"
}
```

---

## Invalid Response

Response 400 Bad Requests

```json
{
  "timestamp": "data",
  "status": 400,
  "error": "Bad Request",
  "message": "User exist!",
  "path": "/api/auth/signup"
}
```

#### Bad request body format
```json
{
  "timestamp": "<date>",
  "status": 400,
  "error": "Bad Request",
  "path": "/api/<controller-context>/<service-name>"
}
```


