/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.baneletpart1;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author nosip
 */
public class BaneleTPart1 {

    public static void main(String[] args) {

        Scanner myInput = new Scanner(System.in);

        System.out.println();
        System.out.println(" REGISTRATION ");
        System.out.println();

        System.out.print("Enter your first name: ");
        String firstName = myInput.nextLine().trim();

        while (!Login.checkFirstName(firstName)) {
            System.out.println("First name is incorrect.");
            System.out.print("Enter your first name again: ");
            firstName = myInput.nextLine().trim();
        }

        System.out.print("Enter your last name: ");
        String lastName = myInput.nextLine().trim();

        while (!Login.checkLastName(lastName)) {
            System.out.println("Last name is incorrect.");
            System.out.print("Enter your last name again: ");
            lastName = myInput.nextLine().trim();
        }

        System.out.print("Enter your phone number: ");
        String phoneNumber = myInput.nextLine().trim();

        while (!Login.checkCellPhoneNumber(phoneNumber)) {
            System.out.println("Cell phone number incorrectly formatted or does not contain the international code.");
            System.out.print("Enter your phone number again: ");
            phoneNumber = myInput.nextLine().trim();
        }

        System.out.print("Enter your username: ");
        String registerUserName = myInput.nextLine().trim();

        while (!Login.checkUserName(registerUserName)) {
            System.out.println("Username is not correctly formatted. Ensure that it contains an underscore and is no more than 5 characters.");
            System.out.print("Enter your username again: ");
            registerUserName = myInput.nextLine().trim();
        }

        System.out.print("Enter your password: ");
        String registerPassword = myInput.nextLine();

        while (!Login.checkPasswordComplexity(registerPassword)) {
            System.out.println("Password is not correctly formatted. Ensure that it contains at least 8 characters, a capital letter, a number, and a special character.");
            System.out.print("Enter your password again: ");
            registerPassword = myInput.nextLine();
        }

        System.out.println();
        System.out.println(Login.registerUser(
                firstName, lastName, phoneNumber,
                registerUserName, registerPassword));

        System.out.println();
        System.out.println(" LOGIN ");
        System.out.println();

        boolean loggedIn = false;

        while (!loggedIn) {

            System.out.print("Enter your username: ");
            String userName = myInput.nextLine().trim();

            System.out.print("Enter your password: ");
            String password = myInput.nextLine();

            loggedIn = Login.loginUser(
                    userName, password,
                    registerUserName, registerPassword);

            System.out.println();
            System.out.println(Login.loginStatus(
                    firstName, lastName,
                    userName, password,
                    registerUserName, registerPassword));

            if (!loggedIn) {
                System.out.println("Please try again.");
                System.out.println();
            }
        }

        System.out.println();
        System.out.println("WELCOME TO QUICKCHAT");

        // Part 3 required collections
        ArrayList<Message> sentMessages = new ArrayList<>();
        ArrayList<Message> disregardedMessages = new ArrayList<>();
        ArrayList<Message> storedMessages = loadStoredMessages();

        ArrayList<String> messageHashes = new ArrayList<>();
        ArrayList<String> messageIDs = new ArrayList<>();

        // Include IDs and hashes of previously stored messages
        for (Message savedMessage : storedMessages) {
            messageHashes.add(savedMessage.getMessageHash());
            messageIDs.add(savedMessage.getMessageID());
        }

        int totalToSend = 0;
        int messagesEntered = 0;
        boolean quit = false;

        while (!quit) {

            int menuOption = showMenu(myInput);

            if (menuOption == 1) {

                if (totalToSend == 0) {

                    System.out.println();
                    System.out.print("How many messages do you want to send? ");
                    totalToSend = readNumber(myInput);

                    while (totalToSend < 1) {
                        System.out.print("Enter a number of 1 or more: ");
                        totalToSend = readNumber(myInput);
                    }
                }

                if (messagesEntered >= totalToSend) {

                    System.out.println();
                    System.out.println("You have reached your limit of "
                            + totalToSend + " message(s).");

                } else {

                    while (messagesEntered < totalToSend) {

                        System.out.println();
                        System.out.println("MESSAGE "
                                + (messagesEntered + 1)
                                + " OF " + totalToSend);
                        System.out.println();

                        String messageID = Message.generateMessageID();

                        // Avoid duplicate message IDs
                        while (messageIDs.contains(messageID)) {
                            messageID = Message.generateMessageID();
                        }

                        System.out.print("Enter the recipient's number: ");
                        String recipient = myInput.nextLine().trim();

                        Message checker = new Message(
                                messageID, messagesEntered,
                                firstName, recipient, "check");

                        while (!Login.checkCellPhoneNumber(recipient)) {

                            System.out.println(
                                    checker.checkRecipientCell(recipient));

                            System.out.print(
                                    "Enter the recipient's number again: ");

                            recipient = myInput.nextLine().trim();
                        }

                        System.out.println(
                                checker.checkRecipientCell(recipient));
                        System.out.println();

                        System.out.print("Enter your message: ");
                        String messageText = myInput.nextLine();

                        while (messageText.trim().isEmpty()
                                || messageText.length() > 250) {

                            if (messageText.trim().isEmpty()) {
                                System.out.println("Message cannot be empty.");
                            } else {
                                System.out.println(
                                        checker.checkMessageLength(messageText));
                            }

                            System.out.print("Enter your message again: ");
                            messageText = myInput.nextLine();
                        }

                        System.out.println(
                                checker.checkMessageLength(messageText));

                        messagesEntered++;

                        Message currentMessage = new Message(
                                messageID,
                                messagesEntered,
                                firstName,
                                recipient,
                                messageText);

                        messageIDs.add(currentMessage.getMessageID());
                        messageHashes.add(currentMessage.getMessageHash());

                        System.out.println();
                        System.out.println(
                                currentMessage.messageIDCreated());

                        System.out.println();
                        System.out.println("---------- MESSAGE OPTIONS ----------");
                        System.out.println("1. Send Message");
                        System.out.println("2. Disregard Message");
                        System.out.println("3. Store Message");
                        System.out.println("-------------------------------------");
                        System.out.print(
                                "What do you want to do with this message? ");

                        int action = readNumber(myInput);

                        while (action < 1 || action > 3) {

                            System.out.println(
                                    currentMessage.SentMessage(action));

                            System.out.print(
                                    "What do you want to do with this message? ");

                            action = readNumber(myInput);
                        }

                        System.out.println();
                        System.out.println(currentMessage.SentMessage(action));

                        if (action == 1) {

                            currentMessage.setMessageStatus("Sent");
                            sentMessages.add(currentMessage);
                            currentMessage.DisplayMesage();

                        } else if (action == 2) {

                            currentMessage.setMessageStatus("Disregard");
                            disregardedMessages.add(currentMessage);

                            System.out.print(
                                    "Press 0 to disregard the message: ");

                            String deleteKey = myInput.nextLine().trim();

                            while (!deleteKey.equals("0")) {

                                System.out.print(
                                        "Press 0 to disregard the message: ");

                                deleteKey = myInput.nextLine().trim();
                            }

                            System.out.println("Message disregarded.");

                        } else {

                            currentMessage.setMessageStatus("Stored");
                            storedMessages.add(currentMessage);

                            currentMessage.StoreMessages(storedMessages);
                        }

                        if (messagesEntered == totalToSend) {

                            System.out.println();
                            System.out.println("MESSAGE LIMIT REACHED");
                            System.out.println(
                                    "Total number of messages sent: "
                                    + sentMessages.size());
                        }
                    }
                }

            } else if (menuOption == 2) {

                displaySentMessages(sentMessages);

            } else if (menuOption == 3) {

                // Refresh stored messages from JSON
                storedMessages = loadStoredMessages();

                messageManagementMenu(
                        sentMessages,
                        storedMessages,
                        disregardedMessages,
                        messageHashes,
                        messageIDs,
                        myInput);

            } else if (menuOption == 4) {

                System.out.println();
                System.out.println("Total number of messages sent: "
                        + sentMessages.size());
                System.out.println("Goodbye.");

                quit = true;

            } else {

                System.out.println();
                System.out.println(
                        "Invalid option. Choose 1, 2, 3 or 4.");
            }
        }

        myInput.close();
    }

    // MAIN MENU
    public static int showMenu(Scanner myInput) {

        System.out.println();
        System.out.println("--------------- QUICKCHAT MENU -------------");
        System.out.println("1) Send Messages");
        System.out.println("2) Show Recently Sent Messages");
        System.out.println("3) Stored Messages - Part 3");
        System.out.println("4) Quit");
        System.out.println("--------------------------------------------");
        System.out.print("Choose an option: ");

        return readNumber(myInput);
    }

    // NUMBER VALIDATION
    public static int readNumber(Scanner myInput) {

        String line = myInput.nextLine().trim();

        while (!line.matches("-?[0-9]{1,9}")) {

            System.out.print("Please enter a number: ");
            line = myInput.nextLine().trim();
        }

        return Integer.parseInt(line);
    }

    // DISPLAY SENT MESSAGES
    public static void displaySentMessages(
            ArrayList<Message> sentMessages) {

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

    // PART 3 - ONE COMBINED MENU
    public static void messageManagementMenu(
            ArrayList<Message> sentMessages,
            ArrayList<Message> storedMessages,
            ArrayList<Message> disregardedMessages,
            ArrayList<String> messageHashes,
            ArrayList<String> messageIDs,
            Scanner myInput) {

        int option = 0;

        while (option != 7) {

            System.out.println();
            System.out.println("--------------- STORED MESSAGES ---------------");
            System.out.println("1) Display Sender and Recipient");
            System.out.println("2) Display the Longest Stored Message");
            System.out.println("3) Search Message by ID");
            System.out.println("4) Search Messages by Recipient");
            System.out.println("5) Delete Message by Hash");
            System.out.println("6) Display Message Report");
            System.out.println("7) Return to Main Menu");
            System.out.println("-----------------------------------------------");
            System.out.print("Choose an option: ");

            option = readNumber(myInput);

            switch (option) {

                case 1:
                    displaySenderRecipient(storedMessages);
                    break;

                case 2:
                    displayLongestMessage(storedMessages);
                    break;

                case 3:
                    searchMessageID(
                            sentMessages, storedMessages, myInput);
                    break;

                case 4:
                    searchRecipient(
                            sentMessages, storedMessages, myInput);
                    break;

                case 5:
                    deleteMessageByHash(
                            storedMessages,
                            messageHashes,
                            messageIDs,
                            myInput);
                    break;

                case 6:
                    displayMessageReport(storedMessages);
                    break;

                case 7:
                    System.out.println("Returning to main menu.");
                    break;

                default:
                    System.out.println("Invalid option. Choose 1 to 7.");
            }
        }
    }

    // PART 3 A - DISPLAY SENDER AND RECIPIENT
    public static void displaySenderRecipient(
            ArrayList<Message> storedMessages) {

        System.out.println();
        System.out.println("---------- SENDER AND RECIPIENT ----------");

        if (storedMessages.isEmpty()) {

            System.out.println("No stored messages available.");

        } else {

            for (int i = 0; i < storedMessages.size(); i++) {

                Message currentMessage = storedMessages.get(i);

                System.out.println();
                System.out.println("MESSAGE " + (i + 1));
                System.out.println("Sender: "
                        + currentMessage.getMessageSender());
                System.out.println("Recipient: "
                        + currentMessage.getRecipient());
            }
        }

        System.out.println("------------------------------------------");
    }

    // PART 3 B - DISPLAY LONGEST STORED MESSAGE
    public static void displayLongestMessage(
            ArrayList<Message> storedMessages) {

        System.out.println();
        System.out.println("---------- LONGEST STORED MESSAGE ----------");

        if (storedMessages.isEmpty()) {

            System.out.println("No stored messages available.");
            return;
        }

        Message longestMessage = storedMessages.get(0);

        for (Message currentMessage : storedMessages) {

            if (currentMessage.getMessage().length()
                    > longestMessage.getMessage().length()) {

                longestMessage = currentMessage;
            }
        }

        System.out.println();
        System.out.println("Longest message:");
        System.out.println(longestMessage.getMessage());

        System.out.println();
        System.out.println("Number of characters: "
                + longestMessage.getMessage().length());

        System.out.println("------------------------------------------");
    }

    // PART 3 C - SEARCH MESSAGE BY ID
    public static void searchMessageID(
            ArrayList<Message> sentMessages,
            ArrayList<Message> storedMessages,
            Scanner myInput) {

        System.out.println();
        System.out.println("---------- SEARCH MESSAGE ID ----------");
        System.out.print("Enter the Message ID: ");

        String searchID = myInput.nextLine().trim();

        ArrayList<Message> allMessages = new ArrayList<>();
        allMessages.addAll(sentMessages);
        allMessages.addAll(storedMessages);

        boolean found = false;

        for (Message currentMessage : allMessages) {

            if (currentMessage.getMessageID().equals(searchID)) {

                System.out.println();
                System.out.println("Message found:");
                System.out.println("Recipient: "
                        + currentMessage.getRecipient());
                System.out.println("Message: "
                        + currentMessage.getMessage());

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No message found with that ID.");
        }
    }

    // PART 3 D - SEARCH BY RECIPIENT
    public static void searchRecipient(
            ArrayList<Message> sentMessages,
            ArrayList<Message> storedMessages,
            Scanner myInput) {

        System.out.println();
        System.out.println("---------- SEARCH RECIPIENT ----------");
        System.out.print("Enter the recipient's cellphone number: ");

        String searchNumber = myInput.nextLine().trim();

        ArrayList<Message> allMessages = new ArrayList<>();
        allMessages.addAll(sentMessages);
        allMessages.addAll(storedMessages);

        boolean found = false;

        for (Message currentMessage : allMessages) {

            if (currentMessage.getRecipient().equals(searchNumber)) {

                System.out.println();
                System.out.println("Matching message:");
                System.out.println("Recipient: "
                        + currentMessage.getRecipient());
                System.out.println("Message: "
                        + currentMessage.getMessage());

                found = true;
            }
        }

        if (!found) {
            System.out.println("No messages found for this recipient.");
        }
    }

    // PART 3 E - DELETE MESSAGE USING HASH
    public static void deleteMessageByHash(
            ArrayList<Message> storedMessages,
            ArrayList<String> messageHashes,
            ArrayList<String> messageIDs,
            Scanner myInput) {

        System.out.println();
        System.out.println("---------- DELETE MESSAGE ----------");

        if (storedMessages.isEmpty()) {

            System.out.println("There are no stored messages to delete.");
            return;
        }

        System.out.print("Enter the Message Hash: ");
        String searchHash = myInput.nextLine().trim();

        boolean found = false;

        for (int i = 0; i < storedMessages.size(); i++) {

            Message currentMessage = storedMessages.get(i);

            if (currentMessage.getMessageHash()
                    .equalsIgnoreCase(searchHash)) {

                System.out.println();
                System.out.println("Message found:");
                currentMessage.DisplayMesage();

                System.out.println();
                System.out.print("Press 0 to confirm deletion: ");

                String confirmation = myInput.nextLine().trim();

                if (confirmation.equals("0")) {

                    storedMessages.remove(i);

                    messageHashes.remove(
                            currentMessage.getMessageHash());

                    messageIDs.remove(
                            currentMessage.getMessageID());

                    // Save the updated collection to JSON
                    currentMessage.StoreMessages(storedMessages);

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

    // PART 3 F - DISPLAY STORED MESSAGES REPORT
    public static void displayMessageReport(
            ArrayList<Message> storedMessages) {

        System.out.println();
        System.out.println("---------- STORED MESSAGES REPORT ----------");

        if (storedMessages.isEmpty()) {

            System.out.println("No stored messages available.");

        } else {

            for (int i = 0; i < storedMessages.size(); i++) {

                Message currentMessage = storedMessages.get(i);

                System.out.println();
                System.out.println("MESSAGE " + (i + 1));
                System.out.println();

                System.out.println("Message ID: "
                        + currentMessage.getMessageID());

                System.out.println("Message Hash: "
                        + currentMessage.getMessageHash());

                System.out.println("Sender: "
                        + currentMessage.getMessageSender());

                System.out.println("Recipient: "
                        + currentMessage.getRecipient());

                System.out.println("Message: "
                        + currentMessage.getMessage());

                System.out.println("Message Status: "
                        + currentMessage.getMessageStatus());
            }
        }

        System.out.println();
        System.out.println("Total stored messages: "
                + storedMessages.size());

        System.out.println("--------------------------------------------");
    }

    // READ STORED MESSAGES FROM JSON
    public static ArrayList<Message> loadStoredMessages() {

        ArrayList<Message> storedMessages = new ArrayList<>();

        File file = new File("message.json");

        if (!file.exists()) {
            return storedMessages;
        }

        Gson gson = new Gson();

        Type messageListType = new TypeToken<ArrayList<Message>>() {
        }.getType();

        try (FileReader reader = new FileReader(file)) {

            ArrayList<Message> loadedMessages =
                    gson.fromJson(reader, messageListType);

            if (loadedMessages != null) {
                storedMessages.addAll(loadedMessages);
            }

        } catch (IOException | com.google.gson.JsonParseException e) {

            System.out.println("Error loading stored messages: "
                    + e.getMessage());
        }

        return storedMessages;
    }
}
