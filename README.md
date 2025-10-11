# CineConnect


### Back-end 
#### Project Structure

````
src/main/java
└── com.cinema.CineConnect
    ├── config      // System configuration and Spring beans definition (e.g., SecurityConfig)
    ├── controller  // Handles incoming HTTP requests and responses (API entry point)
    ├── model       // Defines the data structure/entities (e.g., Client, Employee)
    ├── repository  // Manages direct database access using JdbcClient (data persistence)
    └── service     // Contains the core business logic, validation, and transaction management
````


