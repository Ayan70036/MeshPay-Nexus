# 📡 MeshPay Nexus — Offline UPI Payment Simulation System

MeshPay Nexus is an innovative **offline digital payment simulation platform** built using **Spring Boot** that explores how UPI-style transactions could work even without internet connectivity.

The project is based on the idea of **mesh networking**, where nearby mobile devices act as intermediate nodes to securely forward encrypted transaction packets from sender to receiver.

Instead of depending completely on the internet, MeshPay Nexus simulates a decentralized communication flow where transactions travel across nearby devices until they successfully reach the destination device.

---

# 🌍 Problem Statement

Modern UPI systems completely depend on:
- Internet connectivity
- Bank server communication
- Stable mobile networks

But during:
- Network outages
- Rural connectivity issues
- Disaster situations
- Remote area payments

digital transactions may fail.

MeshPay Nexus explores a futuristic solution:

> “Can nearby devices help transfer payment packets securely without internet?”

This project simulates that concept using backend routing logic, encrypted transaction packets, and device-to-device communication flow.

---

# 🚀 Core Idea Behind MeshPay Nexus

Imagine:

📱 Device A wants to send money to Device D  
But Device D is not directly reachable.

Instead of internet:
- Device B forwards the packet
- Then Device C forwards it
- Finally Device D receives it

This creates a **mesh-style transaction network**.

---

# 🔄 Complete Transaction Flow

```text
Sender Device
      ↓
Transaction Created
      ↓
AES Encryption Applied
      ↓
Packet Generated
      ↓
Nearby Device Detection
      ↓
Mesh Forwarding Begins
      ↓
Intermediate Devices Forward Packet
      ↓
Receiver Device Gets Packet
      ↓
Transaction Verified
      ↓
Balance Updated
```

---

# 🔐 Security Implementation

Security is one of the main focuses of this project.

## ✅ AES Encryption
Every transaction packet is encrypted before forwarding.

## ✅ Secure Packet Transfer
Intermediate devices can only forward packets — they cannot modify secure transaction data.

## ✅ Validation Layer
Receiver validates transaction details before processing payment.

## ✅ Controlled Routing
Transactions only work within configurable communication range.

---

# 📶 Mesh Network Simulation

The project simulates real-world mesh communication concepts:

| Feature | Purpose |
|---|---|
| 📍 Distance Simulation | Simulates Bluetooth/WiFi Direct range |
| 📡 Packet Forwarding | Devices relay transactions |
| 🌐 Internet Toggle | Simulates online/offline devices |
| 🔄 Dynamic Routing | Transfers packets through nearby nodes |
| 📦 Packet Objects | Encapsulate encrypted transaction data |

---

# 🛠️ Tech Stack

| Technology | Usage |
|---|---|
| ☕ Java | Core Backend Development |
| 🌱 Spring Boot | Application Framework |
| 🌐 HTML | Frontend Structure |
| 🎨 CSS | User Interface Styling |
| ⚡ JavaScript | Dynamic Frontend Logic |
| 🔐 AES Encryption | Transaction Security |
| 📦 Maven | Dependency Management |

---

# 🏗️ Backend Architecture

The project follows a layered architecture pattern.

## 📌 Controller Layer
Handles API requests and device communication flow.

## 📌 Service Layer
Contains:
- transaction processing
- packet routing
- mesh forwarding logic
- encryption handling

## 📌 Repository Layer
Stores:
- device data
- balances
- transaction information

## 📌 Simulation Layer
Imitates mesh communication between nearby devices.

---

# 📱 Main Functionalities

# 💸 Offline Money Transfer
Users can perform transactions without internet.

---

# 📡 Mesh Packet Routing
Packets travel through multiple nearby devices.

---

# 📍 Distance-Based Communication
Devices communicate only within configurable range.

---

# 🌐 Internet ON/OFF Simulation
Each simulated device can:
- enable internet
- disable internet
- still participate in mesh routing

---

# 🔄 Real-Time Packet Visualization
The UI demonstrates packet movement and transaction execution.

---

# ⚙️ How To Run The Project

## 1️⃣ Clone Repository

```bash
git clone https://github.com/Ayan70036/MeshPay-Nexus.git
```

---

## 2️⃣ Open In IDE

Recommended:
- IntelliJ IDEA
- Eclipse
- VS Code

---

## 3️⃣ Run Spring Boot Application

```bash
mvn spring-boot:run
```

---

## 4️⃣ Open Browser

```text
http://localhost:8080
```

---

# 🧠 Technical Concepts Demonstrated

✔️ Spring Boot Backend Development  
✔️ REST API Design  
✔️ AES Encryption  
✔️ Mesh Networking Concepts  
✔️ Packet Routing Logic  
✔️ Layered Architecture  
✔️ Real-Time Simulation  
✔️ Secure Transaction Processing  

---

# 🚀 Future Enhancements

✨ Real Bluetooth/WiFi Direct Communication  
✨ Mobile App Integration  
✨ Blockchain-Based Verification  
✨ AI-Based Route Optimization  
✨ QR-Based Offline Payments  
✨ Transaction Analytics Dashboard  
✨ JWT Authentication  
✨ Cloud Deployment  

---

# 🌟 Why This Project Is Unique

Most student payment projects only implement basic CRUD operations.

MeshPay Nexus focuses on:
- networking concepts
- secure packet transfer
- decentralized communication
- offline transaction simulation

This combines:
- backend engineering
- system design
- security concepts
- communication protocols

into one advanced project.

---

# 👨‍💻 Author

## Ayan Malakar

Passionate about Backend Development, Spring Boot, networking concepts, distributed systems, and innovative fintech solutions 🚀

---

# ⭐ Support

If you found this project interesting, consider giving it a ⭐ on GitHub!
