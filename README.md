# IDATT2003_oving5 :octocat: 

[//]: # (TODO: Fill inn your name and student ID)
[//]: # (TODO: Mappe-2025-Marjoni-fj)

**STUDENT NAME = Fredrik Jonathan Marjoni** 

## Project description💻

[//]: # (TODO: Write a short description of your project/product here.)
This project is a GUI Java-based Poker game developed using Maven.  
The goal of the project is to demonstrate correct and effective use of:

- JavaFX
- Java Streams
- Interactive Design
- Unit testing with JUnit 5

## Project structure 📁
The project follows the standard Maven directory structure.  
Source code and tests are clearly separated, and packages are organized by responsibility.

### Main Package Structure (`src/main`)
<pre>
java.edu.ntnu.idi.idatt2003.oving5
  ├── App.java
  ├── ArgumentValidator.java
  ├── CardDisplay.java
  ├── DeckOfCards.java
  ├── HandOfCards.java
  └── PlayingCard.java
recources
  └── cards
    └── (All 52 playing cards).svg
</pre>
[//]: # (TODO: Describe the structure of your project here. How have you used packages in your structure. Where are all sourcefiles stored. Where are all JUnit-test classes stored. etc.)

## JUnit tests 🧪

All unit tests are written using **JUnit 5** and mirror the main package structure.

### Test package structure (`src/test`)
<pre>
java.edu.ntnu.idi.idatt2003.oving5
  ├── AppTest.java
  ├── ArgumentValidatorTest.java
  ├── DeckOfCardsTest.java
  ├── HandOfCardsTest.java
  └── PlayingCardTest.java
</pre>

The tests cover:
- Normal use cases
- Edge cases
- Invalid input (e.g. `null` and empty strings)
---
## Maven layout ⚙️
The project uses the standard Maven layout:

- `src/main/java` – application source code
- `src/test/java` – unit tests
- `pom.xml` – project configuration and dependencies

This ensures:
- Clean separation of production and test code
- Easy integration with IDEs (VS Code, IntelliJ, Eclipse)
- Simple build and test execution
---
## Link to repository📚

[//]: # (TODO: Include a link to your GitHub repository here.)
[GitHub Repository - IDATT2003 oving 5](https://github.com/Marjoni-fj/IDATT2003_oving5)

---
## How to run the project📝

[//]: # (TODO: Describe how to run your project here. What is the main class? What is the main method?
What is the input and output of the program? What is the expected behaviour of the program?)

### Requirements
- Java JDK 25
- JavaFX
- Maven
- An IDE such as VS Code or IntelliJ

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/Marjoni-fj/IDATT2003_oving5.git
   
2. **Open the Project:**  
   Open VS Code and select **File > Open Folder**, navigating to the root folder of the project (containing `pom.xml`).
   ````bash
   cd IDATT2003_oving5
   
4. **Build the Project:**  
   Open the terminal in VS Code (`Ctrl + ~`) and run:  
   ```bash
   mvn clean compile
   
5. **Run the Application:**
   #### Expected behavior:
   The program opens up a window where the user can deal a card and test their luck. After a user has dealt their card, they can check the hand, and a small check is executed on the user's hand.
   
   Start the program by running the main class:
    ```bash
   mvn javafx:run
---
## How to run the tests 🧪
This project uses JUnit 5 for unit testing. 
All test classes mirror the main package structure and are stored in `src/test/java/edu/ntnu`

- ### Open the Project:
   Open VS Code and select **File > Open Folder**, navigating to the root folder of the project (containing `pom.xml`).
  ````bash
   cd IDATT2003_oving5

- ### Run all tests
   To execute the full test suite, run:
   ```bash
   mvn clean test

This command:
   1. Cleans old build files
   2. Compiles the main source code
   3. Compiles the tests
   4. Runs all JUnit tests
---
 - ### Viewing test results
After the tests finish, Maven creates detailed reports here: 
`target/surefire-reports/`

Each report includes:
   1. Test class summaries
   2. Stack traces for any failures
   3. Execution times
   4. Running tests in an IDE

[//]: # (TODO: Describe how to run the tests here.)

## References 🔗
[//]: # (TODO: Include references here, if any. For example, if you have used code from the course book, include a reference to the chapter.
Or if you have used code from a website or other source, include a link to the source.)
Course textbook and lecture materials
Maven documentation: https://maven.apache.org/
JUnit 5: https://junit.org/junit5/

---
"""
