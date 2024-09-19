<p align="center"><img src="assets/demure.png" style="width: 100%;" /> </p>

<h1 align="center">Demure: Bicycle Rental System</h1>

### Overview

**"Demure"** is a bicycle rental platform that allows users to rent bicycles, track their health stats, and manage stations. Users can be either **Admins** (who manage stations and docks) or **Riders** (who rent bicycles and track health data). Riders can log in, select stations near their current location and destination, reserve a bike, and monitor their ride. The platform is designed with a microservices architecture for flexibility, scalability, and maintainability.

### Technologies Used

- **Back-End Microservices**:  
  - **User Management Service**: `Java`, `Spring Boot`, `PostgreSQL`, `JWT`, `Bcrypt`, `Redis`
  - **Station Management Service**: `TypeScript`, `Node`, `Neo4j`, `GraphQL`
  - **Ride Management Service**: `Go`, `Redis`, `Kafka`
  - **Health & Analytics Service**: `Python`, `FastAPI`, `MongoDB`
  - **Billing Service**: `JavaScript`, `Nest`, `MySQL`, `Stripe API`
  
- **Front-End**:
  - `React` (for user interface)
  - `TypeScript` (for typing and error reduction)
  - `Redux` (for state management)

- **API Gateway**: `Nginx`
- **Service Communication**: `gRPC` (internal), `REST` (external)
- **Machine Learning**: `Scikit-Learn`, `Statesmodels`, `MLxtend`
- **Containerization**: `Docker`
- **CI/CD**: `GitHub Actions`, `Jenkins`
- **Monitoring**: `Prometheus`, `Grafana`
- **Logging**: `Elasticsearch`, `Logstash`, `Kibana` (ELK Stack)

### Functional Requirements
1. **User Roles**:
   - **Admin**: Manages stations and docks.
   - **Rider**: Registers, logs in, reserves bicycles, and tracks health data.

2. **Bicycle Reservations**:
   - Riders can reserve bicycles at stations with availability and choose docks near their destination.
   - 20-minute window for reservation confirmation.

3. **Health Tracking**:
   - Real-time tracking of speed, calories burned, time, distance, and a post-ride health report.

4. **Station Management**:
   - Admins can add, update, and manage stations and docks.

5. **Notifications**:
   - Riders receive notifications about reservation status and reminders.

6. **Payment Processing**:
   - Fare calculation and payments using a secure gateway (Stripe, PayPal).

### Non-Functional Requirements
1. **Scalability**:
   - The platform must scale to handle multiple users and rides concurrently.
   - Use of **Kafka** for asynchronous processing, and **Redis** for caching.

2. **Security**:
   - Secure password storage with **Bcrypt**.
   - Use of **JWT** for user authentication and role-based access control (RBAC).
   - Secure payment processing following **PCI-DSS** standards.

3. **Reliability**:
   - High availability with **distributed microservices** and **fault-tolerant patterns** like Circuit Breaker.
   - Implement a **retry mechanism** for failed processes (e.g., billing or reservation).

4. **Performance**:
   - **Low-latency** real-time updates using **WebSockets**.
   - Efficient database queries, using **Redis** for caching and **Neo4j** for managing relationships.

5. **Maintainability**:
   - Modular and loosely coupled services that can be independently developed, tested, and deployed.
   - Each service adheres to **best coding practices**, with **well-documented APIs**.
