# Train Timetable Announcement System

This project is a Java-based application that announces train arrival information using Text-to-Speech (TTS) technology. It connects to a MySQL database to fetch train details such as train number, train name, base station, destination station, and platform number.

## Features

- Fetches train details from a MySQL database based on user input (train number).
- Uses FreeTTS (a text-to-speech library) to announce the arrival of trains at a specific platform.
- Provides voice announcements in a clear and consistent format.
- Ensures easy interaction through console-based input and output.

## Technologies Used

- **Java**: The primary programming language used for this application.
- **FreeTTS**: A speech synthesis system written entirely in Java that is used to convert text to speech.
- **MySQL**: A relational database management system used to store train details.
- **JDBC**: Java Database Connectivity API used to connect and execute queries with MySQL.

## Prerequisites

- JDK 8 or higher installed on your system.
- MySQL server installed and running.
- FreeTTS library added to the classpath.
- Internet connection for library and dependencies setup.

## Setup and Installation

1. **Clone the Repository**:
   
   ```bash
   git clone https://github.com/Optimas-CSE23L197/India_Railway_Announcement
   cd train-timetable-announcement-system
Set Up the Database:

Update Database Connection Details:

Modify the database connection details in the Function class:

private final String url = "jdbc:mysql://localhost:3306/database_name";
private final String user = "root";
private final String pass = "your_password";

Create a database in MySQL called train_timetable.

Create a table named new_train_table using the following SQL schema:
<--------------sql---------->
CREATE TABLE new_train_table (
  id INT AUTO_INCREMENT PRIMARY KEY,
  Train_no INT,
  Train_name VARCHAR(255),
  Base_Station VARCHAR(255),
  Destination_Station VARCHAR(255)
);


### Notes:

1. **Customize the README**: Replace `<repository-url>` with the actual URL of your Git repository. Adjust database table names, column names, and other details as per your actual database schema and project structure.

2. **Dependencies**: Ensure that paths to JAR files (like FreeTTS and MySQL Connector/J) are correct based on where you have stored them.

3. **Usage Instructions**: Modify the usage instructions according to your project's actual functionality and setup process.

4. **Contributions and License**: Update the `Contributing` and `License` sections as per your project requirements or policies.

This `README.md` provides a comprehensive overview of the project and serves as a guide for setting it up, running it, and understanding its purpose.
