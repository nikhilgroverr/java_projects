# OnlineTest 

This project is a Java-based Online Test application, which interacts with a MySQL database to fetch quiz questions and options, allowing users to participate in a quiz. The application includes a timer for each question and tracks the user's score.

Files Overview
The project consists of the following Java files:

1. remoteclient.java
Purpose: This is the main entry point of the application. It handles the overall quiz flow, displays questions, tracks answers, manages the timer, and displays the final score.
Key Features:
Connects to the MySQL database to fetch quiz questions.
Displays questions and answer choices.
Handles the timer (30 seconds per question).
Tracks user answers and calculates score.

2. details.java
Purpose: This class holds the details of each quiz question, including the question text, multiple answer choices, and the correct answer.
Key Features:
Stores the question, answers, and the correct answer for each quiz.
Used by remoteclient to fetch question data for display.

3. server.java
Purpose: This class is responsible for establishing a connection to the MySQL database and fetching quiz data.
Key Features:
Establishes a connection to the database using JDBC.
Fetches quiz data (questions, options, and correct answers) from the quiz table in the database.

4. login.java
Purpose: Manages the login and user authentication process.
Key Features:
Allows users to log in by entering a username.
Saves the user's score in the database once the quiz is finished.

5. database.java
Purpose: Handles all database interactions such as inserting, updating, and fetching data from the MySQL database.
Key Features:
Executes SQL queries to fetch quiz data.
Inserts user scores into the login table after completing the quiz.

6. QuizTimer.java
Purpose: Manages the countdown timer for each quiz question.
Key Features:
Starts a timer for each question.
Updates the UI with the remaining time.
Automatically moves to the next question when the timer expires.


1. Clone the Repository
Clone this repository to your local machine:

bash
git clone https://github.com/yourusername/OnlineTest.git

2. Set Up the Database

Run the following SQL commands to set up the database and tables:

sql
Copy code
CREATE DATABASE exam;
USE exam;

CREATE TABLE quiz (
    id INT AUTO_INCREMENT PRIMARY KEY,
    category VARCHAR(255),
    question TEXT,
    ans1 VARCHAR(255),
    ans2 VARCHAR(255),
    ans3 VARCHAR(255),
    ans4 VARCHAR(255),
    correct_ans VARCHAR(255)
);

CREATE TABLE login (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    attempt VARCHAR(255),
    marks INT
);

3. Modify Database Connection
Ensure the remoteclient.java file has the correct MySQL connection URL:


conn = DriverManager.getConnection("jdbc:mysql://localhost/exam", "root", "");
4. Compile and Run
After setting up the database, compile the Java files:

bash

javac *.java

Run the application:

bash

java remoteclient

Usage:
Launch the app and input your username.
Choose a quiz category and start answering questions.
The timer counts down for each question (30 seconds).
Once the quiz is finished, your score is displayed and saved to the database.
