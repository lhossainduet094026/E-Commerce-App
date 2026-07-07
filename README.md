# E-Commerce-App
A distributed microservices-based e-commerce platform demonstrating event-driven architecture, Saga choreography, API Gateway, and resilient service communication.

## 🧩 Microservices Architecture

### 🚪 API Gateway
- Single entry point for all client requests
- Routes requests to internal microservices

---

### 🧾 Order Service
- Handles order creation and order lifecycle
- Publishes order events
- Initiates distributed workflow using Saga pattern

---

### ⚙️ Product Orchestrator Service
- Coordinates between Product and Inventory services
- Handles orchestration logic for product-related workflows
- Plays a key role in Saga-based processing

---

### 📦 Inventory Service
- Manages stock and inventory updates
- Handles stock deduction during order processing
- Ensures consistency in distributed transactions

---

### 📖 Product Read Service (CQRS)
- Read-optimized service for product listing APIs
- Implements CQRS (Command Query Responsibility Segregation)
- Provides fast and scalable product query responses

---

## 🔄 Architecture Patterns Used

- Microservices Architecture
- API Gateway Pattern
- Event-Driven Architecture (Kafka)
- CQRS Pattern (Product Read Service)
- Saga Pattern (Choreography - In Progress)

---

## 🔁 System Flow

1. Client places order via API Gateway  
2. Order Service creates order  
3. Order event is published to Kafka  
4. Product Orchestrator processes business logic  
5. Inventory Service updates stock  
6. Product Read Service serves optimized product data  
7. System maintains eventual consistency using events  

---

## 🛠️ Tech Stack

- Java 17+
- Spring Boot
- Spring Cloud Gateway
- Spring Data JPA
- Apache Kafka
- MySQL
- Docker
- Microservices Architecture

---

## 🚀 How to Run

### Prerequisites
- Java 17+
- Maven
- Docker & Docker Compose
- Kafka (or Docker-based setup)

---

### Start Infrastructure
```bash
docker-compose up -d
Build Project
mvn clean install -DskipTests

Run Services

API Gateway
cd api-gateway
mvn spring-boot:run
Order Service
cd order-service
mvn spring-boot:run
Product Orchestrator Service
cd product-orchestrator-service
mvn spring-boot:run
Inventory Service
cd inventory-service
mvn spring-boot:run
Product Read Service
cd product-read-service
mvn spring-boot:run

📌 Current Focus
Implementing Saga Choreography using Kafka
Improving distributed consistency between services
Enhancing system resilience and scalability

📈 Future Enhancements
Notification Service (Email/SMS)
Payment Service integration
Circuit Breaker & Retry mechanisms
Centralized logging (ELK / Grafana)
Advanced Saga compensation flows

🎯 Purpose

This project demonstrates:

Real-world microservice architecture
Event-driven distributed systems
CQRS and Saga patterns
Scalable backend system design
Production-grade Java backend development

📌 Status

🚧 Active development project
Focus: Distributed systems + Kafka-based Saga pattern
