# PayPal Integration with Spring Boot

This project demonstrates the integration of PayPal payment services with a Spring Boot application. It includes essential configurations and implementations for handling PayPal transactions seamlessly.

![Screenshot 2024-07-23 132217](https://github.com/user-attachments/assets/a1363501-0d08-4cc0-befc-5a22bf3e69b6)

![Screenshot 2024-07-23 132047](https://github.com/user-attachments/assets/6b803aa2-9664-403a-9878-4ea7319718b6)

## Key Features

- **Maven POM Setup**
  - Added dependencies required for PayPal integration and Spring Boot application.
- **Configuration**
  - Added application properties for PayPal API.
  - Configured Thymeleaf for front-end templating.
- **Controller**
  - `PaypalController`: Manages PayPal payment requests and responses.
- **Service**
  - `PaypalService`: Handles the business logic for PayPal transactions.
- **HTML Templates**
  - Basic templates using Thymeleaf for user interactions.
- **Spring Boot Application Setup**
  - `PaypalIntegrationApplication`: Initializes the Spring Boot application.

## Getting Started

To get started with this project, follow these steps:

1. **Clone the Repository**
    ```sh
    git clone https://github.com/your-repository/paypal-integration-spring-boot.git
    ```

2. **Navigate to the Project Directory**
    ```sh
    cd paypal-integration-spring-boot
    ```

3. **Build the Project**
    ```sh
    mvn clean install
    ```

4. **Run the Application**
    ```sh
    mvn spring-boot:run
    ```

## Project Structure

The project structure is organized as follows:

- **src/main/java/com/yourcompany/paypalintegration**
  - **config**
    - `PaypalConfig.java`: Configures the PayPal API context.
  - **controller**
    - `PaypalController.java`: Manages PayPal payment requests and responses.
  - **service**
    - `PaypalService.java`: Handles the business logic for PayPal transactions.
  - **PaypalIntegrationApplication.java**: Initializes the Spring Boot application.

- **src/main/resources**
  - **application.properties**: Contains PayPal API and Thymeleaf configuration properties.
  - **templates**
    - `index.html`: Basic template for user interaction.
    - Other HTML templates for the application.

## Dependencies

- **Spring Boot**
- **PayPal SDK**
- **Thymeleaf**
- **Maven**

This project serves as a comprehensive guide for integrating PayPal payments into a Spring Boot application, covering everything from basic configurations to handling transactions.

https://github.com/user-attachments/assets/87a5ca82-843a-4824-af68-295d2726b44c

