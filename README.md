# README: Setup & Run Application

## ** Step 1: Setup & Run the Database (MySQL)**

### **1. Install MySQL**
#### **Windows**
- **Option 1:** Download and install from [MySQL official site](https://dev.mysql.com/downloads/installer/)
- **Option 2:** Install via Chocolatey:
  ```sh
  choco install mysql -y
  ```

#### **macOS**
- Install via Homebrew:
  ```sh
  brew install mysql
  ```

#### **Linux (Ubuntu/Debian)**
```sh
sudo apt update
sudo apt install mysql-server -y
```

---

### **2. Start MySQL Server**
#### **Windows**
```sh
net start mysql
```

#### **macOS (Homebrew)**
```sh
brew services start mysql
```

#### **Linux**
```sh
sudo systemctl start mysql
```

---

### **3. Run SQL Script to Create the Database**
**Login to MySQL and run the script**
   ```sh
   mysql -u root < be\src\main\resources\todo_list_schema.sql
   ```

---

## ** Step 2: Run the Backend (Spring Boot)**

### **1. Install Maven**
#### **Windows**
```sh
choco install maven -y
```
#### **macOS**
```sh
brew install maven
```
#### **Linux**
```sh
sudo apt install maven -y
```

---

### **2. Build and Run the Spring Boot Application**
1. **Navigate to the backend directory**
   ```sh
   cd be
   ```
2. **Clean and install dependencies**
   ```sh
   mvn clean install -U
   ```
3. **Start the application**
   ```sh
   mvn spring-boot:run
   ```

---

## ** Step 3: Run the Frontend**

### **1. Install Node.js & npm**
#### **Windows**
Download from [Node.js official site](https://nodejs.org/) or install via package managers.

#### **macOS (Homebrew)**
```sh
brew install node
```

#### **Linux**
```sh
sudo apt install nodejs npm -y
```
---

### **2. Install Frontend Dependencies**
1. **Navigate to the frontend directory**:
   ```sh
   cd todo_fe
   ```
2. **Install dependencies**:
   ```sh
   npm install
   ```
3. **Start the frontend server**:
   ```sh
   npm run dev
   ```

---

## ** Summary of Commands**
| **Step**  | **Windows** | **macOS** | **Linux** |
|-----------|------------|-----------|-----------|
| **Install MySQL** | `choco install mysql` | `brew install mysql` | `sudo apt install mysql-server` |
| **Start MySQL** | `net start mysql` | `brew services start mysql` | `sudo systemctl start mysql` |
| **Run SQL Script** | `mysql -u root  < todo_list_schema.sql` | `mysql -u root < todo_list_schema.sql` | `mysql -u root < todo_list_schema.sql` |
| **Install Maven** | `choco install maven` | `brew install maven` | `sudo apt install maven` |
| **Run Backend** | `mvn clean install && mvn spring-boot:run` | `mvn clean install && mvn spring-boot:run` | `mvn clean install && mvn spring-boot:run` |
| **Install Node.js & npm** | `choco install nodejs` | `brew install node` | `sudo apt install nodejs npm` |
| **Run Frontend** | `npm install && npm run start` | `npm install && npm run start` | `npm install && npm run start` |

---

## ** Application URLs**
After running the backend and frontend, access the application:
- **Backend (Spring Boot API):** `http://localhost:8080`
- **Frontend (React/Angular/Vue):** `http://localhost:5173`

---
