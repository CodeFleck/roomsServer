The authentication mechanism chosen was spring security with JWT, for the reliability of the libraries and convenience of implementation.

**POST to register a new user:**

`curl -H "Content-Type: application/json" -X POST -d '{
     "username": "admin",
     "password": "password"
 }' http://127.0.0.1:8080/users/sign-up`                   
           
**logs into the application (JWT is generated):**

`curl -i -H "Content-Type: application/json" -X POST -d '{
     "username": "admin",
     "password": "password"
 }' http://127.0.0.1:8080/login`   
 
**GET request to retrieve items where xxx.yyy.zzz is substituted by your generated JWT token:**

`curl -H "Authorization: Bearer xxx.yyy.zzz" http://localhost:8080/api/professionals` 