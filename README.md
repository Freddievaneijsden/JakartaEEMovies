## Movie Management

Movie management is a RESTful web service developed using Jakarta EE 11 preview. It manages a collection of movies and provides CRUD functionality(Creat, Read, Update, Delete). It is also possible to filter movies based on various attributes.

## Features

### 🎬 CRUD Operations
- **Create**: Add new movies.
- **Read**: Retrieve details of movies.
- **Update**: Modify existing movies.

### 🔍 Filtering
- Filter movies based on:
    - **Titel**
    - **Director**
    - **Duration**

### 🛠️ Validation
- Data validation using **Jakarta Bean Validation** for ensuring valid input.

### ⚠️ Error Handling
- Custom exception classes and **ExceptionMappers** are implemented for effective error handling.

### 🧪 Testing
- Unit tests for **service** and **mapper** classes to ensure correctness.

### 📦 Containerization
- Using **Docker** and **Docker Compose** for easy deployment and environment setup.

## Steps to Build and Run the Application

### 1. Clone the Repository
git clone https://github.com/Freddievaneijsden/JakartaEEMovies.git

cd JakartaEEMovies

### 2. Build the Application with Maven
Ensure Maven is installed on your machine.

mvn clean package

### 3. Ensure Docker is Running
Make sure Docker is installed and running in the background.

### 4. Start the Database and Server with
docker-compose --profile production up

## Accessing the Application
The application will be accessible at http://localhost:8080.