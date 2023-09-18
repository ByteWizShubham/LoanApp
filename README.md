
# Loan Management Application

This is a simple Loan Management Application built using Spring Boot. It provides a set of RESTful endpoints for managing loans and retrieving loan-related information.

Certainly, here's the API reference with all the endpoints in a table format suitable for pasting into a README file:

## API Reference

| Endpoint                                 | Method | Description                                      |
|------------------------------------------|--------|--------------------------------------------------|
| `/loans`                                 | GET    | Retrieve a list of all loans in the system.     |
| `/loans/add`                             | POST   | Add a new loan to the system.                    |
| `/loans/{loanID}`               | GET    | Retrieve loan details by specifying the Loan ID. |
| `/loans/customers/{customerID}`           | GET    | Retrieve loans associated with a specific customer by specifying the customer ID. |
| `/loans/lenders/{lenderID}`              | GET    | Retrieve loans associated with a specific lender by specifying the lender ID. |
| `/loans/aggregate/lender`                 | GET    | Retrieve aggregate loan information grouped by lender. |
| `/loans/aggregate/interest`              | GET    | Retrieve aggregate loan information grouped by interest rate. |
| `/loans/aggregate/customer`              | GET    | Retrieve aggregate loan information grouped by customer. |

## Usage
Can use any REST client or tool, such as Postman or CURL, to interact with the endpoints provided by this Loan Management Application.

Please note that you may need to replace http://localhost:8080 with the actual hostname and port where your application is running.


## Installation and Setup

Clone or download this repository to your local machine.
Make sure you have Java and Maven installed.
Navigate to the project directory and run the following command to build and run the application:

```bash
  mvn spring-boot:run

```
The application should now be running locally, and you can access the endpoints as described above.
    
## Dependencies

This application uses Spring Boot and may have additional dependencies specified in the `pom.xml` file.




## Contributing

Contributions are always welcome!

If you would like to contribute to this project or report any issues, please feel free to create a pull request or open an issue on the GitHub repository.

Thank you for using the Loan Management Application!


