# wsecu-test
WSECU Programming Test

## Installation and Start Up
### Installation
```sh
git clone https://github.com/rdelhommer/wsecu-test
```

### Start Up
Run the command below from the root directory (wsecu-test) of the cloned git repo. After running the command, the server will be running at localhost:8080
```sh
mvn spring-boot:run
```

## API Reference
### Create a new user

#### Method
POST - http://localhost:8080/user
#### Parameters
* username - The user's unique username
* name - The user's name
* email - The user's email
#### Returns
The created user

### Get an existing user
#### Method
GET - http://localhost:8080/user/{username}
#### Parameters
* username - The user's unique username
#### Returns
The user whose username matches the one provided

### Update the name or email of an existing user
#### Method
PUT - http://localhost:8080/user/{username}
#### Parameters
* username - The user's unique username
#### Returns
The updated user

### Delete an existing user
#### Method
DELETE - http://localhost:8080/user/{username}
#### Parameters
* username - The user's unique username
#### Returns
The deleted user
