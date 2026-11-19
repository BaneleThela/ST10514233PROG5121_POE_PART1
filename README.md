# QuickChat – Java Messaging Application

## 1. Project Overview

QuickChat is a console-based messaging application developed in Java. The application allows users to register, log in, send messages, store messages, and manage previously saved messages.

The project demonstrates the use of object-oriented programming, classes, methods, arrays and ArrayLists, input validation, file handling, JSON storage, and unit testing.

The application guides the user through registration and login before displaying the main QuickChat menu.

## 2. Main Features

QuickChat includes the following features:

### User Registration
- Captures the user's first name and last name.
- Validates the user's cellphone number.
- Checks that the username contains an underscore and is no longer than five characters.
- Checks password complexity.
- Displays whether registration was successful.

### User Login
- Requests the registered username and password.
- Checks the entered details against the registration details.
- Displays a welcome message when login is successful.
- Allows the user to try again when login fails.

### Message Management
- Allows the user to choose how many messages they want to enter.
- Generates a unique, randomly generated ten-digit message ID.
- Validates the recipient's South African cellphone number.
- Limits messages to a maximum of 250 characters.
- Generates a message hash using the message ID, message number, and the first and last words of the message.
- Allows the user to send, disregard, or store a message.

### Stored Messages – Part 3
The application provides additional message-management features:

1. Display the sender and recipient of stored messages.
2. Display the longest stored message.
3. Search for a message using its message ID.
4. Search for messages using a recipient's cellphone number.
5. Delete a stored message using its message hash.
6. Display a report containing stored message details.

### JSON File Storage
QuickChat uses the Gson library to save stored messages in a file named `message.json`.

- Stored messages are written to the JSON file.
- Previously saved messages are loaded when the application starts.
- Updated stored-message collections are saved after storing or deleting messages.

## 3. Technologies and Tools Used

- **Programming Language:** Java
- **IDE:** Apache NetBeans
- **Testing Framework:** JUnit 5
- **JSON Library:** Google Gson
- **Data Structures:** ArrayList
- **Input Handling:** Java Scanner
- **File Handling:** FileReader and FileWriter

## 4. Project Structure

The project uses the package:

`com.mycompany.baneletpart1`

The main source files are:

| File | Purpose |
|---|---|
| `BaneleTPart1.java` | Main application, registration, login, menus, and message management |
| `Login.java` | Registration, username, password, cellphone number, and login validation |
| `Message.java` | Message creation, message IDs, message hashes, validation, printing, and JSON storage |

The test files include:

| Test File | Purpose |
|---|---|
| `MessageTest.java` | Tests message validation, message IDs, hashes, message actions, and message printing |
| `TestRegistrationAndLogin.java` | Tests registration and login validation |
| `Part3Tests.java` | Tests message collections, searching, deletion, longest-message selection, and reporting |

The `message.json` file is created when messages are stored. It contains the saved message information.

## 5. System Requirements

Before running the application, make sure you have:

- Java Development Kit (JDK) installed.
- Apache NetBeans IDE installed.
- The Google Gson library added to the project.
- JUnit 5 configured for running the unit tests.

The project requires Gson because the `Message` class uses it to convert message objects into JSON and save them to a file.

## 6. How to Open and Run the Project

Follow these steps to run QuickChat in NetBeans.

### Step 1: Open NetBeans
Start Apache NetBeans on your computer.

### Step 2: Open the Project
1. Select **File** from the menu.
2. Select **Open Project**.
3. Locate the QuickChat project folder.
4. Select the project.
5. Click **Open Project**.

### Step 3: Check the Project Files
Expand the project in the Projects window.

Locate the Java source package:

`com.mycompany.baneletpart1`

Check that the following files are available:

- `BaneleTPart1.java`
- `Login.java`
- `Message.java`

Also check that the test files are available in the test package.

### Step 4: Configure the Gson Library
Make sure the Google Gson library is included in the project dependencies.

Without Gson, the application will not compile because the messaging classes use Gson for JSON storage.

### Step 5: Run the Application
1. Open `BaneleTPart1.java`.
2. Locate the `main` method.
3. Right-click inside the file.
4. Select **Run File**, or run the project using NetBeans.

The console will display the registration section.

## 7. How to Use QuickChat

### Step 1: Register a User

Enter the requested information:

- First name
- Last name
- South African cellphone number
- Username
- Password

The application validates each entry before proceeding.

**Username requirement:** The username must contain an underscore and must not exceed five characters.

**Password requirements:**
- At least eight characters.
- At least one uppercase letter.
- At least one number.
- At least one special character.

**Cellphone number requirement:** The number must use the South African international format, beginning with `+27`, followed by nine digits.

### Step 2: Log In

Enter the username and password created during registration.

If the details match, QuickChat displays a welcome message and opens the main menu.

If the details do not match, the application requests another login attempt.

### Step 3: Select an Option from the Main Menu

The main menu contains four options:

| Option | Description |
|---|---|
| 1 | Send Messages |
| 2 | Show Recently Sent Messages |
| 3 | Stored Messages – Part 3 |
| 4 | Quit |

### Step 4: Send a Message

When selecting option 1:

1. Enter the number of messages you want to send.
2. Enter the recipient's cellphone number.
3. Enter the message.
4. The application validates the recipient and message length.
5. A message ID and message hash are generated.
6. Choose what to do with the message.

The available message actions are:

- **Option 1 – Send Message:** Sends the message and adds it to the sent messages collection.
- **Option 2 – Disregard Message:** Disregards the message.
- **Option 3 – Store Message:** Saves the message to the stored messages collection and JSON file.

Messages cannot be empty or exceed 250 characters.

### Step 5: View Recently Sent Messages

Select option 2 from the main menu.

The application displays the messages that have been sent during the current session.

### Step 6: Manage Stored Messages

Select option 3 from the main menu to access the Part 3 message-management menu.

The menu provides six functions:

1. **Display Sender and Recipient:** Displays the sender and recipient details of stored messages.
2. **Display the Longest Stored Message:** Finds and displays the stored message containing the most characters.
3. **Search Message by ID:** Searches sent and stored messages using a message ID.
4. **Search Messages by Recipient:** Finds messages belonging to a specified recipient.
5. **Delete Message by Hash:** Finds a stored message using its hash and asks for confirmation before deletion.
6. **Display Message Report:** Displays stored message IDs, hashes, senders, recipients, message content, and message statuses.

Select option 7 to return to the main menu.

### Step 7: Quit the Application

Select option 4 from the main menu.

QuickChat displays the total number of messages sent during the current session and exits the application.

## 8. Message Validation and Identification

QuickChat uses several validation and identification methods.

### Message ID
Each new message receives a randomly generated ten-digit ID.

### Message Hash
The message hash is generated using:
- The first two digits of the message ID.
- The message number.
- The first word of the message.
- The last word of the message.

The resulting hash is converted to uppercase.

### Message Length
Messages must contain no more than 250 characters.

### Recipient Validation
Recipient cellphone numbers must follow the `+27` international format and contain nine digits after the country code.

## 9. Unit Testing

JUnit 5 is used to test the application's functionality.

The project includes three main test classes.

### MessageTest.java
Tests include:
- Successful and unsuccessful message-length validation.
- Correct and incorrect recipient cellphone numbers.
- Message hash generation.
- Message ID generation and validation.
- Sending, disregarding, and storing messages.
- Returning the total number of sent messages.
- Printing message information.

### TestRegistrationAndLogin.java
Tests include:
- Username validation.
- Password complexity validation.
- Cellphone number validation.
- Successful login.
- Login failure handling.

### Part3Tests.java
Tests include:
- Correct population of sent-message collections.
- Finding the longest stored message.
- Searching for a message using its ID.
- Searching for messages using a recipient number.
- Deleting messages using their hashes.
- Generating a stored-message report.

### How to Run the Unit Tests

1. Open the QuickChat project in NetBeans.
2. Expand the **Test Packages** folder.
3. Locate the test class you want to run.
4. Right-click the test class.
5. Select **Test File**.
6. Review the results in the Test Results window.

Run all test classes to check the application's validation and message-management functionality.

## 10. Data Storage

QuickChat uses an ArrayList to manage messages while the application is running.

Three main message collections are used:

- `sentMessages` – holds messages that have been sent.
- `storedMessages` – holds messages saved for later use.
- `disregardedMessages` – holds messages that have been disregarded.

The application also maintains collections of message IDs and message hashes.

Stored messages are saved in `message.json` using Gson. When QuickChat starts, it checks whether the JSON file exists and loads its contents if available.

**Important:** Keep `message.json` in the application's working directory so that previously stored messages can be loaded correctly.

## 11. Conclusion

QuickChat is a Java console-based messaging application that combines user registration, login validation, message creation, and message management.

The project demonstrates object-oriented programming, data validation, ArrayList collections, JSON file storage, message identification, searching, deletion, and unit testing.

The application provides a practical demonstration of how Java can be used to develop a basic messaging system while maintaining and managing message information.
