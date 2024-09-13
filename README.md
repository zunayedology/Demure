<p align="center"><img src="global/demure.png" style="width: 100%;" /> </p>

<h1 align="center">Demure: Bicycle Rental System</h1>

## 1. Core Features
* ### 1.1 Bicycle Rental System
    * **Station Management**: Each station will be managed, showing available docks and bicycles in real-time.
    * **Route Optimization**: Use machine learning to suggest optimal routes for the rider based on traffic, distance, and health goals.
    * **Rental Process**: Riders can book bicycles, pick them up from one station, and drop them off at another. You can incorporate real-time availability tracking.
    * **Smart Pricing**: Dynamic pricing based on demand, time, and distance, integrating with a payment system.
* ### 1.2 Health Tracking
    * **Calorie Burn Calculation**: Calculate calories burned based on distance, speed, and rider profile using fitness algorithms.
    * **Real-time Health Reports**: Display health stats (calories, distance, speed, etc.) for riders post-ride.
    * **Health Goals**: Let riders set personal health goals, which the app will track and suggest personalized ride plans.



## 2. Technologies
### 2.1 Backend
* `Gin (Go)` + `gRPC`: For core functionalities like booking, live GPS handling, etc.
* `Spring Boot (Java)` + `JWT` + `REST`: For user authentication and authorization.
* `Node (JS)` + `Apollo Server` + `GraphQL`: For CRUD operations.
* `FastAPI (Python)` + `REST`: For health and analytics services.
### 2.2 Frontend
* `React (TS)` + `TailwindCSS` + `Vite`: For type-safe and rich user interface.
* `Vercel`: For hosting the frontend.
### 2.3 Database
* `Neo4j` + `GraphQL`: Graph database for geospatial data and routing.
* `PostgreSQL` + `Prisma` | `GORM`: Relational database for user profiles, ride history, transactions, etc.
* `Redis`: Caching System for reducing load on main databases.
### 2.4 Machine Learning
* `Scikit-Learn (KNN)`: For optimized route predictions.
* `StatsModels (ARIMA)`: For reasonable fare estimation.
* `MLxtend (Apriori)`:  For stable marketing analysis.
### 2.5 DevOps
* `Docker`: For containerized microservices.
* `Nginx`: For load balancing and reverse proxying.
* `GitHub Actions`: Implement continuous integration and deployment. 



## 3. Comparison with Alternatives
* **Smart Health Recommendations**: While many bicycle-sharing platforms focus only on renting, this app adds unique value by integrating fitness tracking and personalized health recommendations.
* **Route Optimization with AI**: Leverage AI to calculate optimal routes for riders based on traffic data and rider health goals.
* **Dynamic Pricing**: This can provide a competitive edge by incentivizing off-peak usage or high-demand stations.



## 4. Business Values
* **Health-conscious Urban Commuters**: Encourages more people in Dhaka to adopt cycling as an affordable and healthy commuting option.
* **Environmental Impact**: Promotes green transportation in a congested city.
* **Scalable Business Model**: The platform can later integrate electric bikes or scooters, expanding the business model.



## **5. Functional Requirements**

### **5.1 User Registration and Authentication**
- **Description**: Users must be able to create an account, log in, and manage their profile.
  - **Use Case**: Register, login, password reset.
  - **Requirements**:
    - Users can create accounts with basic details (name, email, password).
    - Implement JWT-based authentication.
    - Integration with Google/Facebook for social login (optional).
    - Users can update their profile, password, and view their ride history.

### **5.2 Bicycle Booking**
- **Description**: Users should be able to view available bicycles and book one from the nearest station.
  - **Use Case**: Request bicycle, view station, book ride.
  - **Requirements**:
    - Users can request to book a bicycle.
    - The system suggests nearby stations with available bicycles based on the user’s location.
    - Users can select the station, bicycle, and receive a route to their destination.
    - Riders must start their ride within 20 minutes of booking.

### **5.3 Real-Time Station and Bicycle Availability**
- **Description**: Users can view the real-time availability of bicycles at any station.
  - **Requirements**:
    - The system updates the number of available bicycles and docks at each station in real-time.
    - Stations show the current number of free docks to park bicycles.
  
### **5.4 GPS-Based Tracking**
- **Description**: Track bicycle rides using GPS to monitor routes, distances, and durations.
  - **Use Case**: Monitor ride progress, check route, track distance.
  - **Requirements**:
    - Every bicycle is GPS-enabled, and the system tracks the real-time location of riders.
    - Users are charged extra for exceeding the estimated ride time or taking a longer route.

### **5.5 Route Suggestions and Estimations**
- **Description**: Provide users with the best route to their destination, including time, distance, and calories to burn.
  - **Requirements**:
    - The system provides optimal routes based on the user’s destination, traffic conditions, and available docks at the destination station.
    - Estimated travel time, fare, and calories to burn are shown before starting the ride.

### **5.6 Health and Fitness Reports**
- **Description**: Generate health metrics for users based on their rides (calories burned, distance traveled, etc.).
  - **Requirements**:
    - Users receive detailed reports on calories burned, duration, and distance for each ride.
    - Integration with third-party fitness apps (optional) to sync data.

### **5.7 Payment Processing**
- **Description**: Facilitate secure and easy payments for rides.
  - **Requirements**:
    - Users can add payment methods (credit card, mobile payment).
    - The system calculates the fare based on distance, time, and additional charges if applicable.
    - Users are notified of the total charge and can confirm payment.
  
### **5.8 Station Management (Admin/Guard)**
- **Description**: Admins and station masters must be able to manage station operations and bicycle maintenance.
  - **Requirements**:
    - Admins can monitor the performance of stations (available docks, bicycles, staff).
    - Station masters are responsible for bicycle maintenance and reporting any issues.

### **5.9 Notifications**
- **Description**: Notify users about their bookings, ride status, or payment receipts.
  - **Requirements**:
    - Users receive notifications for booking confirmations, ride completion, fare, etc.
    - Push notifications for important alerts (e.g., delayed ride, additional charges).



## **6. Non-Functional Requirements**

### **6.1 Performance**
- **Description**: The system must provide fast, real-time responses, especially during peak usage times.
  - **Requirements**:
    - System response times should be under 2 seconds for key operations (e.g., booking a bicycle, viewing bicycle availability).
    - The platform should handle high traffic loads with hundreds of users booking bicycles simultaneously.
    - Efficient caching (via Redis) should be used for frequently accessed data (e.g., station availability).

### **6.2 Scalability**
- **Description**: The platform must be able to scale as more users and stations are added.
  - **Requirements**:
    - Microservices architecture (Node.js, Go, Spring Boot) should be implemented for independent scaling of different system components.
    - AWS-based infrastructure should allow for auto-scaling to handle increased traffic.

### **6.3 Security**
- **Description**: Ensure user data, payments, and system access are secure.
  - **Requirements**:
    - Implement SSL/TLS encryption for data transfer.
    - Use JWT-based authentication to secure user sessions.
    - Adhere to OWASP guidelines for secure code practices.
    - Regular security audits and penetration testing should be performed.

### **6.4 Availability**
- **Description**: The system must be available 99.9% of the time to provide reliable service.
  - **Requirements**:
    - Implement failover mechanisms and redundant services using AWS for high availability.
    - Regular system backups should be maintained.

### **6.5 Maintainability**
- **Description**: The system should be easy to maintain and extend with new features.
  - **Requirements**:
    - Use a modular architecture with well-documented code.
    - Follow clean coding practices and utilize version control (Git).
    - Continuous integration (CI) pipelines should automate testing and deployments.

### **6.6 Usability**
- **Description**: The system should provide a user-friendly and intuitive interface.
  - **Requirements**:
    - Design the frontend with a responsive layout (TailwindCSS) to support mobile and web users.
    - Conduct usability testing to ensure smooth navigation and clear functionality for riders.

### **6.7 Data Consistency and Integrity**
- **Description**: Ensure all data (e.g., bookings, payments, ride history) is accurate and consistent across the system.
  - **Requirements**:
    - Use database transactions in PostgreSQL to ensure atomicity and consistency.
    - Implement real-time synchronization of station data (available docks/bicycles) with Redis.

### **6.8 Localization**
- **Description**: Support multiple languages for users from different regions.
  - **Requirements**:
    - The platform should initially support English and Bangla, with an option to add more languages as needed.
  
### **6.9 Compliance**
- **Description**: Ensure the system complies with local regulations regarding user data and payment processing.
  - **Requirements**:
    - Adhere to GDPR principles for data privacy.
    - Implement secure payment gateways that comply with PCI DSS standards.
