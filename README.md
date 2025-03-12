# Boston Bus Delay Tracker

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring20Boot-3.x-green)
![MBTA API](https://img.shields.io/badge/MBTA20API-v3-orange)
![License](https://img.shields.io/badge/License-MIT-yellow)

The **Boston Bus Delay Tracker** is a Spring Boot application that allows users to check real-time bus delays in Boston using data from the MBTA API. It provides a user-friendly interface to view delays for all routes or a specific route, along with a search history feature.

---

## Features

- **Real-Time Delay Tracking**: Fetch and display real-time bus delays from the MBTA API.
- **Route-Specific Delays**: Check delays for a specific bus route by entering the route number.
- **Search History**: Track recent searches and the number of delays found.
- **Professional UI**: A clean and responsive user interface built with Bootstrap and Thymeleaf.
- **Database Integration**: Store search history in an H2 in-memory database.

---

## Technologies Used

- **Backend**:
  - Java 17
  - Spring Boot 3.x
  - Spring WebFlux (for MBTA API integration)
  - H2 Database (for search history)
  - Thymeleaf (for server-side rendering)

- **Frontend**:
  - Bootstrap 5 (for styling)
  - JavaScript (for form handling)

- **APIs**:
  - [MBTA API v3](https://api-v3.mbta.com/docs/swagger/index.html)

---

## Prerequisites

Before running the project, ensure you have the following installed:

- **Java Development Kit (JDK) 17**
- **Maven** (for dependency management and building the project)
- **An MBTA API Key** (get one from the [MBTA Developer Portal](https://api-v3.mbta.com/))

---

## Setup and Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/judedcunha/BostonBusDelayTracker.git
   cd BostonBusDelayTracker
   ```

2. **Add Your MBTA API Key**:
   - Open `src/main/resources/application.properties`.
   - Replace `your_api_key` with your actual MBTA API key:
     ```properties
     mbta.api.key=your_api_key
     ```

3. **Build the Project**:
   ```bash
   mvn clean install
   ```

4. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```
   Alternatively, you can run it using any IDE you prefer. I used VSCode.

5. **Access the Application**:
   - Open your browser and navigate to:
     ```
     http://localhost:8080
     ```

---

## Usage

1. **Home Page**:
   - Enter a bus route number (e.g., `1`, `66`) or leave the field blank to check delays for all routes.
   - Click **Check Delays**.

2. **Delays Page**:
   - View a list of delays for the selected route(s).
   - The page displays the route, delay description, and status.

3. **Search History**:
   - Recent searches are displayed on the home page, showing the route and the number of delays found.

---

## Project Structure

```
src
├── main
│   ├── java
│   │   └── com
│   │       └── jude
│   │           └── bostonbusdelay
│   │               ├── config
│   │               │    └── MBTAApiConfig.java
│   │               ├── controller
│   │               │    └── MBTAApiConfig.java
│   │               ├── exception
│   │               │    ├── GlobalExceptionHandler.java
│   │               │    └── ResourceNotFoundException.java
│   │               ├── model
│   │               │    ├── DelayDTO.java
│   │               │    ├── MBTAAlert.java
│   │               │    ├── MBTAAlertAttributes.java
│   │               │    ├── MBTAAlertResponse.java
│   │               │    └── SearchHistory.java
│   │               ├── repository
│   │               │    └── SearchHistoryRepository.java
│   │               ├── service
│   │               │    └── MBTAService.java
│   │               ├── BostonbusdelayApplication.java
|   |               └── DemoApplication.java (This is a placeholder file and can be ignored)
│   └── resources
│       ├── static/
│       │   ├── css/
│       │   └── js/
│       ├── templates/
│       │   ├── index.html
│       │   ├── delays.html
|       |   └── error.html
│       └── application.properties
└── test
    └── java
        └── com
            └── jude
                └── bostonbusdelay
```

---

## API Endpoints

| Endpoint              | Description                          |
|-----------------------|--------------------------------------|
| `GET /`               | Home page with search form           |
| `GET /delays`         | Display delays for all routes        |
| `GET /delays?route=1` | Display delays for a specific bus route  |

---

## Database

The application uses an **H2 in-memory database** to store search history. You can access the H2 console at:
```
http://localhost:8080/h2-console
```

- **JDBC URL**: `jdbc:h2:mem:bustracker`
- **Username**: `sa`
- **Password**: (leave blank)

---

## Contributing

Contributions are welcome! If you'd like to contribute, please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Commit your changes.
4. Submit a pull request.

---

## License

This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for details.

---

## Acknowledgments

- Data provided by the [MBTA API](https://api-v3.mbta.com/).
- Built with [Spring Boot](https://spring.io/projects/spring-boot).

