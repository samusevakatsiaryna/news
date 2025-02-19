# 📰 Trading News Portal

## 📌 Overview
The **Trading News Portal** is a **Java-based web application** designed to manage and display trading-related news articles. It follows a **layered architecture** and utilizes **JSP, Servlets, and JDBC** for dynamic content rendering and database operations. The project allows **users to read news**, while **administrators** have additional functionalities such as **adding, editing, and deleting news articles**.

## 🔧 Tech Stack
- **Backend:** Java (Servlets, JSP, JDBC)  
- **Frontend:** HTML, CSS, JSP (Bootstrap for styling)  
- **Database:** PostgreSQL  
- **Web Server:** Apache Tomcat  
- **Build Tool:** Maven  

## 🚀 Features
✔ **User Authentication:** Login system with session management  
✔ **Role-based Access Control:** Admins can modify content, users can only read  
✔ **News Management:**  
   - 📌 Add news (Admins only)  
   - ✏ Edit news (Admins only)  
   - 🗑 Delete news (Admins only)  
✔ **Dynamic Content Rendering:** JSP with JSTL for seamless updates  
✔ **Custom Connection Pool:** Efficient database handling with resource management  
✔ **Exception Handling & Logging:** Proper logging and structured error handling  
✔ **AJAX-powered Editing:** Inline news editing without page refresh  

## 🎯 How It Works
1. **Users can log in** and browse the latest trading news.  
2. **Admins** have additional controls to **add, edit, or delete news**.  
3. **News articles are stored in a PostgreSQL database**, categorized by type (Crypto, ETFs, Stocks).  
4. **Connection pooling** ensures efficient database interactions.  
5. **Exception handling** ensures smooth operation with meaningful error messages.  

## 🛠 Setup & Deployment
### 🔹 Prerequisites:
- Install **Java 21+**
- Install **Apache Tomcat 10+**
- Install **PostgreSQL** and create a database
- Configure `db.properties` with your **database credentials**
  
### 🔹 Running the Project:
1. Clone the repository  
   ```sh
   git clone https://github.com/yourusername/trading-news-portal.git
