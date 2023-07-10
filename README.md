# Bitespeed-Backend-Task-Identity-Reconciliation

Bitespeed Backend task: Identity Reconciliation, with Spring Boot backend and Sql Server DB


# Application is deployed in EC2
http://35.175.222.17:3000/identify
(will not be available from 14th July 2023)


## API Endpoint 

The web service takes two arguments, i.e. email and phoneNumber. 

#### URL to send POST request

```http
  POST /identify
```

| Parameter | Type     | Description                    |
| :-------- | :------- | :------------------------- |
| `email`   | `string` | Email address of the customer (Optional)|
| `phoneNumber` | `number` | Phone number of the customer (Optional)| 


Returns an HTTP 200 response with a JSON payload containing the consolidated contact.


```json
{
	"contact":{
		"primaryContatctId": number,
		"emails": string[], // first element being email of primary contact 
		"phoneNumbers": number[], // first element being phoneNumber of priary contact
		"secondaryContactIds": number[] // Array of all Contact IDs that are "seondary" to the primary contact
	}
}
```
#### Example 
Request:

```json
{
	"email": "mcfly@hillvalley.edu",
	"phoneNumber": "123456"
}
```

Success Response:

```json
{
  "contact": {
    "primaryContactId": 1,
    "emails": ["primary@example.com", "secondary@example.com"],
    "phoneNumbers": ["1234567890"],
    "secondaryContactIds": [2, 3]
  }
}
```

## Features

- Consolidates customer contacts based on email and phone number
- Supports identifying new customers and creating primary contacts
- Maintains primary and secondary contact relationships
- Provides an endpoint for identifying customers based on contact 

## Tech stack used

- SpringBoot
- MySQL
- Hibernate ORM for MySQL
- Docker
- AWS EC2

## Install instructions

### Prerequisites

Make sure you have the following dependencies installed on your machine:

- Docker

### Getting Started

To set up and run the FluxKart Customer Identification Web Service on your local machine, follow these steps:

1. Clone this repository to your local machine:

```bash
git clone https://github.com/Gopalakrishnan20/Bitespeed-Backend-Task-Identity-Reconciliation-.git
```

2. Change into the project's directory

```bash
cd Bitespeed-Backend-Task-Identity-Reconciliation-
```

3. Start the MySQL database and web service using Docker:
```bash
docker build -t qwerty098qwer/identityreconciliation:1.0 .
sudo docker-compose up
```
(now open localhost:8080/identify and hit post request to this API endpoint)




## Authors

- [@Gopalakrishnan J](https://github.com/Gopalakrishnan20)
