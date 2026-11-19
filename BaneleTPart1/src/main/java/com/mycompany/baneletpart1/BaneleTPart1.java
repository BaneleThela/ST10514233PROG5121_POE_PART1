
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.baneletpart1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author nosip
 */
public class BaneleTPart1 {

    public static void main(String[] args) {

        Scanner myInput = new Scanner(System.in);

        String firstName;
        String lastName;
        String phoneNumber;
        String registerUserName;
        String registerPassword;
        String userName;
        String password;

        System.out.println();
        System.out.println(" REGISTRATION ");
        System.out.println();

        // Keep asking until a valid first name is entered
        System.out.print("Enter your first name: ");
        firstName = myInput.nextLine().trim();

        while (!Login.checkFirstName(firstName)) {
            System.out.println("First name is incorrect.");
            System.out.print("Enter your first name again: ");
            firstName = myInput.nextLine().trim();
        }

        // Keep asking until a valid last name is entered
        System.out.print("Enter your last name: ");
        lastName = myInput.nextLine().trim();

        while (!Login.checkLastName(lastName)) {
            System.out.println("Last name is incorrect.");
            System.out.print("Enter your last name again: ");
            lastName = myInput.nextLine().trim();
        }

        // Keep asking until a valid phone number is entered
        System.out.print("Enter your phone number: ");
        phoneNumber = myInput.nextLine().trim();

        while (!Login.checkCellPhoneNumber(phoneNumber)) {
            System.out.println("Cell phone number incorrectly formatted or does not contain the international code.");
            System.out.print("Enter your phone number again: ");
            phoneNumber = myInput.nextLine().trim();
        }

        // Keep asking until a valid username is entered
        System.out.print("Enter your username: ");
        registerUserName = myInput.nextLine().trim();

        while (!Login.checkUserName(registerUserName)) {
            System.out.println("Username is not correctly formatted. Ensure that it contains an underscore and is no more than 5 characters.");
            System.out.print("Enter your username again: ");
            registerUserName = myInput.nextLine().trim();
        }

        // Keep asking until a valid password is entered
        System.out.print("Enter your password: ");
        registerPassword = myInput.nextLine();

        while (!Login.checkPasswordComplexity(registerPassword)) {
            System.out.println("Password is not correctly formatted. Ensure that it contains "
                    + "at least 8 characters, a capital letter, a number, and a special character.");
            System.out.print("Enter your password again: ");
            registerPassword = myInput.nextLine();
        }

        System.out.println();
        System.out.println(Login.registerUser(firstName, lastName, phoneNumber,
                registerUserName, registerPassword));

        System.out.println();
        System.out.println(" LOGIN ");
        System.out.println();

        boolean loggedIn = false;

        // Repeat the login process until the details are correct
        while (!loggedIn) {

            System.out.print("Enter your username: ");
            userName = myInput.nextLine().trim();

            System.out.print("Enter your password: ");
            password = myInput.nextLine();

            loggedIn = Login.loginUser(userName, password,
                    registerUserName, registerPassword);

            System.out.println();
            System.out.println(Login.loginStatus(firstName, lastName,
                    userName, password, registerUserName, registerPassword));

            if (!loggedIn) {
                System.out.println("Please try again.");
                System.out.println();
            }
        }

        // QUICKCHAT
        System.out.println();
        System.out.println("WELCOME TO QUICKCHAT");

        int totalToSend = 0;

        ArrayList<Message> sentList = new ArrayList<>();
        ArrayList<Message> storedList = new ArrayList<>();

        int messagesEntered = 0;
        boolean quit = false;

        // Keep showing the menu until the user chooses Quit
        while (!quit) {

            int menuOption = showMenu(myInput);

            if (menuOption == 1) {

                // Ask how many messages to send when Option 1 is selected
                if (totalToSend == 0) {

                    System.out.println();
                    System.out.print("How many messages do you want to send? ");
                    totalToSend = readNumber(myInput);

                    while (totalToSend < 1) {
                        System.out.print("Enter a number of 1 or more: ");
                        totalToSend = readNumber(myInput);
                    }
                }

                if (messagesEntered == totalToSend) {

                    System.out.println();
                    System.out.println("You have reached your limit of "
                            + totalToSend + " message(s).");

                } else {

                    // Continue creating messages until the limit is reached
                    while (messagesEntered < totalToSend) {

                        System.out.println();
                        System.out.println("MESSAGE " + (messagesEntered + 1)
                                + " OF " + totalToSend);
                        System.out.println();

                        String messageID = Message.generateMessageID();

                        // Keep asking until a valid recipient number is entered
                        System.out.print("Enter the recipient's number: ");
                        String recipient = myInput.nextLine().trim();

                        Message checker = new Message(messageID,
                                messagesEntered, firstName, recipient, "check");

                        while (!Login.checkCellPhoneNumber(recipient)) {

                            System.out.println(checker.checkRecipientCell(recipient));
                            System.out.print("Enter the recipient's number again: ");
                            recipient = myInput.nextLine().trim();
                        }

                        System.out.println(checker.checkRecipientCell(recipient));
                        System.out.println();

                        // Validate the message text
                        System.out.print("Enter your message: ");
                        String messageText = myInput.nextLine();

                        while (messageText.trim().isEmpty()
                                || messageText.length() > 250) {

                            if (messageText.trim().isEmpty()) {
                                System.out.println("Message cannot be empty.");
                            } else {
                                System.out.println(checker.checkMessageLength(messageText));
                            }

                            System.out.print("Enter your message again: ");
                            messageText = myInput.nextLine();
                        }

                        System.out.println(checker.checkMessageLength(messageText));

                        messagesEntered++;

                        Message currentMessage = new Message(messageID,
                                messagesEntered, firstName, recipient, messageText);

                        System.out.println();
                        System.out.println(currentMessage.messageIDCreated());

                        System.out.println();
                        System.out.println("---------- MESSAGE OPTIONS ----------");
                        System.out.println("1. Send Message");
                        System.out.println("2. Disregard Message");
                        System.out.println("3. Store Message");
                        System.out.println("-------------------------------------");
                        System.out.print("What do you want to do with this message? ");

                        int action = readNumber(myInput);

                        while (action < 1 || action > 3) {

                            System.out.println(currentMessage.SentMessage(action));
                            System.out.print("What do you want to do with this message? ");
                            action = readNumber(myInput);
                        }

                        System.out.println();
                        System.out.println(currentMessage.SentMessage(action));

                        if (action == 1) {

                            currentMessage.setMessageStatus("Sent");
                            sentList.add(currentMessage);
                            currentMessage.DisplayMesage();

                        } else if (action == 2) {

                            currentMessage.setMessageStatus("Disregard");

                            System.out.print("Press 0 to delete the message: ");
                            String deleteKey = myInput.nextLine().trim();

                            while (!deleteKey.equals("0")) {

                                System.out.print("Press 0 to delete the message: ");
                                deleteKey = myInput.nextLine().trim();
                            }

                            System.out.println("Message deleted.");

                        } else {

                            currentMessage.setMessageStatus("Stored");
                            storedList.add(currentMessage);
                            currentMessage.StoreMessages(storedList);
                        }

                        if (messagesEntered == totalToSend) {

                            System.out.println();
                            System.out.println("MESSAGE LIMIT REACHED");
                            System.out.println("Total number of messages sent: "
                                    + currentMessage.returnTotalMessages(sentList));
                        }
                    }
                }

            } else if (menuOption == 2) {

                displaySentMessages(sentList);

            } else if (menuOption == 3) {

                messageManagementMenu(sentList, storedList, myInput);

            } else if (menuOption == 4) {

                System.out.println();
                System.out.println("Total number of messages sent: "
                        + sentList.size());
                System.out.println("Goodbye.");

                quit = true;

            } else {

                System.out.println();
                System.out.println("Invalid option. Choose 1, 2, 3 or 4.");
            }
        }

        myInput.close();
    }

    // Shows the QuickChat menu
    public static int showMenu(Scanner myInput) {

        System.out.println();
        System.out.println("--------------- QUICKCHAT MENU -------------");
        System.out.println("1) Send Messages");
        System.out.println("2) Show Recently Sent Messages");
        System.out.println("3) Part 3 - Message Management");
        System.out.println("4) Quit");
        System.out.println("----------------------------------------------");
        System.out.print("Choose an option: ");

        return readNumber(myInput);
    }

    // Reads a whole number
    public static int readNumber(Scanner myInput) {

        String line = myInput.nextLine().trim();

        while (!line.matches("-?[0-9]{1,9}")) {

            System.out.print("Please enter a number: ");
            line = myInput.nextLine().trim();
        }

        return Integer.parseInt(line);
    }

    // Display recently sent messages
    public static void displaySentMessages(ArrayList<Message> sentMessages) {

        System.out.println();
        System.out.println("---------- RECENTLY SENT MESSAGES ----------");

        if (sentMessages.isEmpty()) {

            System.out.println("No messages have been sent yet.");

        } else {

            for (int i = 0; i < sentMessages.size(); i++) {

                System.out.println();
                System.out.println("MESSAGE " + (i + 1));
                System.out.println();

                sentMessages.get(i).DisplayMesage();
            }
        }

        System.out.println("--------------------------------------------");
    }

    // PART 3 - All features in one menu
    public static void messageManagementMenu(
            ArrayList<Message> sentMessages,
            ArrayList<Message> storedMessages,
            Scanner myInput) {

        int option = 0;

        while (option != 7) {

            System.out.println();
            System.out.println("--------------- MESSAGE MANAGEMENT -------------");
            System.out.println("1) Display Sender and Recipient of Sent Messages");
            System.out.println("2) Display the Longest Sent Message");
            System.out.println("3) Search for a Message by Message ID");
            System.out.println("4) Search for Messages by Recipient");
            System.out.println("5) Delete a Message by Message Hash");
            System.out.println("6) Display Sent Messages Report");
            System.out.println("7) Return to Main Menu");
            System.out.println("------------------------------------------------");
            System.out.print("Choose an option: ");

            option = readNumber(myInput);

            switch (option) {

                case 1:
                    displaySenderRecipient(sentMessages);
                    break;

                case 2:
                    displayLongestMessage(sentMessages);
                    break;

                case 3:
                    searchMessageID(sentMessages, myInput);
                    break;

                case 4:
                    searchRecipient(sentMessages, myInput);
                    break;

                case 5:
                    deleteMessageByHash(sentMessages, myInput);
                    break;

                case 6:
                    displayMessageReport(sentMessages, "SENT MESSAGES REPORT");
                    break;

                case 7:
                    System.out.println("Returning to main menu.");
                    break;

                default:
                    System.out.println("Invalid option. Choose 1 to 7.");
            }
        }
    }

    // Part 3 A - Display sender and recipient of sent messages
    public static void displaySenderRecipient(ArrayList<Message> sentMessages) {

        System.out.println();
        System.out.println("---------- SENDER AND RECIPIENT ----------");

        if (sentMessages.isEmpty()) {

            System.out.println("No sent messages available.");

        } else {

            for (int i = 0; i < sentMessages.size(); i++) {

                Message currentMessage = sentMessages.get(i);

                String details = getMessageDetails(currentMessage);

                System.out.println();
                System.out.println("MESSAGE " + (i + 1));

                System.out.println("Sender: "
                        + getDetail(details, "Sender:"));

                System.out.println("Recipient: "
                        + getDetail(details, "Recipient:"));
            }
        }

        System.out.println("------------------------------------------");
    }

    // Part 3 B - Display the longest sent message
    public static void displayLongestMessage(ArrayList<Message> sentMessages) {

        System.out.println();
        System.out.println("---------- LONGEST SENT MESSAGE ----------");

        if (sentMessages.isEmpty()) {

            System.out.println("No sent messages available.");

        } else {

            Message longestMessage = sentMessages.get(0);

            String longestText = getDetail(
                    getMessageDetails(longestMessage), "Message:");

            for (int i = 1; i < sentMessages.size(); i++) {

                Message currentMessage = sentMessages.get(i);

                String currentText = getDetail(
                        getMessageDetails(currentMessage), "Message:");

                if (currentText.length() > longestText.length()) {

                    longestMessage = currentMessage;
                    longestText = currentText;
                }
            }

            System.out.println();
            System.out.println("Longest message:");
            System.out.println(longestText);
            System.out.println();
            System.out.println("Number of characters: " + longestText.length());
        }

        System.out.println("------------------------------------------");
    }

    // Part 3 C - Search for a message using its ID
    public static void searchMessageID(ArrayList<Message> sentMessages,
            Scanner myInput) {

        System.out.println();
        System.out.println("---------- SEARCH MESSAGE ID ----------");
        System.out.print("Enter the Message ID: ");

        String searchID = myInput.nextLine().trim();
        boolean found = false;

        for (int i = 0; i < sentMessages.size(); i++) {

            Message currentMessage = sentMessages.get(i);

            String details = getMessageDetails(currentMessage);

            String currentID = getDetail(details, "Message ID:");

            if (currentID.equals(searchID)) {

                System.out.println();
                System.out.println("Message found:");
                currentMessage.DisplayMesage();

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No message found with that ID.");
        }
    }

    // Part 3 D - Search for messages using the recipient number
    public static void searchRecipient(ArrayList<Message> sentMessages,
            Scanner myInput) {

        System.out.println();
        System.out.println("---------- SEARCH RECIPIENT ----------");
        System.out.print("Enter the recipient's cellphone number: ");

        String searchNumber = myInput.nextLine().trim();
        boolean found = false;

        for (int i = 0; i < sentMessages.size(); i++) {

            Message currentMessage = sentMessages.get(i);

            String details = getMessageDetails(currentMessage);

            String currentRecipient = getDetail(details, "Recipient:");

            if (currentRecipient.equals(searchNumber)) {

                System.out.println();
                System.out.println("Matching message:");
                currentMessage.DisplayMesage();

                found = true;
            }
        }

        if (!found) {
            System.out.println("No messages found for this recipient.");
        }
    }

    // Part 3 E - Delete a sent message using its hash
    public static void deleteMessageByHash(ArrayList<Message> sentMessages,
            Scanner myInput) {

        System.out.println();
        System.out.println("---------- DELETE MESSAGE ----------");

        if (sentMessages.isEmpty()) {

            System.out.println("There are no sent messages to delete.");
            return;
        }

        System.out.print("Enter the Message Hash: ");
        String searchHash = myInput.nextLine().trim();

        boolean found = false;

        for (int i = 0; i < sentMessages.size(); i++) {

            Message currentMessage = sentMessages.get(i);

            String details = getMessageDetails(currentMessage);

            String currentHash = getDetail(details, "Message Hash:");

            if (currentHash.equalsIgnoreCase(searchHash)) {

                System.out.println();
                System.out.println("Message found:");
                currentMessage.DisplayMesage();

                System.out.println();
                System.out.print("Press 0 to confirm deletion: ");

                String confirmation = myInput.nextLine().trim();

                if (confirmation.equals("0")) {

                    sentMessages.remove(i);

                    System.out.println();
                    System.out.println("Message successfully deleted.");

                } else {

                    System.out.println("Message deletion cancelled.");
                }

                found = true;
                break;
            }
        }

        if (!found) {

            System.out.println("No message found with that hash.");
        }
    }

    // Part 3 F - Display a report of sent messages
    public static void displayMessageReport(ArrayList<Message> messages,
            String reportTitle) {

        System.out.println();
        System.out.println("---------- " + reportTitle + " ----------");

        if (messages.isEmpty()) {

            System.out.println("No messages available.");

        } else {

            for (int i = 0; i < messages.size(); i++) {

                System.out.println();
                System.out.println("MESSAGE " + (i + 1));
                System.out.println();

                System.out.println(getMessageDetails(messages.get(i)));
            }
        }

        System.out.println();
        System.out.println("Total messages: " + messages.size());
        System.out.println("--------------------------------------------");
    }

    // Get the details of one message using the existing Message class
    public static String getMessageDetails(Message currentMessage) {

        ArrayList<Message> oneMessage = new ArrayList<>();
        oneMessage.add(currentMessage);

        return currentMessage.printMessages(oneMessage);
    }

    // Read a specific field from the message details
    public static String getDetail(String details, String fieldName) {

        String[] lines = details.split("\\R");

        for (String line : lines) {

            String trimmedLine = line.trim();

            if (trimmedLine.startsWith(fieldName)) {

                return trimmedLine.substring(fieldName.length()).trim();
            }
        }

        return "";
    }
}
