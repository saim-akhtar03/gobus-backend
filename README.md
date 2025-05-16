# 🚌 Bus Ticket Booking System

A robust **Bus Ticket Booking System** built using **Spring Boot** and **PostgreSQL**. This system supports user registration, admin operations, schedule and seat management, email notifications, and a smart priority seat feature.

---

## 🚀 Features

### ✅ Core Functionality
- JWT-based User Authentication
- Role-based access (Admin/User)
- Admin can:
  - Create buses and schedules
  - View all bookings
- Users can:
  - View available schedules
  - Book tickets with real-time seat availability
  - Cancel tickets

### 💺 Smart Seat Management
- Automatic seat assignment based on availability
- **Priority Seat Upgrade Feature**:
  - If a user gets a seat at the back and enables "priority seat"
  - If a front seat becomes available due to cancellation
  - That user will **automatically be upgraded** to the better seat

### 📧 Email Notifications
- Users receive email notifications for:
  - Ticket Booking Confirmation
  - Ticket Cancellation
  - Priority Seat Upgrades

---

## 🛠️ Tech Stack

- **Backend**: Spring Boot
- **Database**: PostgreSQL
- **Security**: Spring Security + JWT
- **Email**: JavaMailSender (SMTP configuration)
- **Build Tool**: Maven or Gradle

---

## 📂 Project Structure

