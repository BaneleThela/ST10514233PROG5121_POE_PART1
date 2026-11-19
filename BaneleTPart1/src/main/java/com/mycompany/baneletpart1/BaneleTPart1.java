
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
            System.out.println("Cell phone number incorrectly formatted or " + "does not contain the international code.");
            System.out.print("Enter your phone number again: ");
            phoneNumber = myInput.nextLine().trim();
        }

        // Keep asking until a valid username is entered
        System.out.print("Enter your username: ");
        registerUserName = myInput.nextLine().trim();
        while (!Login.checkUserName(registerUserName)) {
            System.out.println(
                    "Username is not correctly formatted. Ensure that it " + "contains an underscore and is no more than 5 characters.");
            System.out.print("Enter your username again: ");
            registerUserName = myInput.nextLine().trim();
        }

        // Keep asking until a valid password is entered
        System.out.print("Enter your password: ");
        registerPassword = myInput.nextLine();
        while (!Login.checkPasswordComplexity(registerPassword)) {
            System.out.println("Password is not correctly formatted. Ensure that it contains " +
                    "at least 8 characters, a capital letter, a number, " + "and a special character.");
            System.out.print("Enter your password again: ");
            registerPassword = myInput.nextLine();
        }

        System.out.println();
        System.out.println(Login.registerUser(firstName, lastName, phoneNumber, registerUserName, registerPassword));

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

            loggedIn = Login.loginUser(userName, password, registerUserName, registerPassword);

            System.out.println();
            System.out.println(
                    Login.loginStatus(firstName, lastName, userName, password, registerUserName, registerPassword));

            if (!loggedIn) {
                System.out.println("Please try again.");
                System.out.println();
            }
        }

        // QUICKCHAT (Part 2) - only reached once the user has logged in
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
                    System.out.println("You have reached your limit of " + totalToSend + " message(s).");
                } else {

                    // Keep creating messages until the selected number is reached
                    while (messagesEntered < totalToSend) {

                        System.out.println();
                        System.out.println("MESSAGE " + (messagesEntered + 1) + " OF " + totalToSend);
                        System.out.println();

                        String messageID = Message.generateMessageID();

                        // Keep asking until a valid recipient number is entered
                        System.out.print("Enter the recipient's number: ");
                        String recipient = myInput.nextLine().trim();

                        Message checker = new Message(messageID, messagesEntered, firstName, recipient, "check");

                        while (!Login.checkCellPhoneNumber(recipient)) {
                            System.out.println(checker.checkRecipientCell(recipient));
                            System.out.print("Enter the recipient's number again: ");
                            recipient = myInput.nextLine().trim();
                        }

                        System.out.println(checker.checkRecipientCell(recipient));
                        System.out.println();

                        // Keep asking until the message is not empty and no more than 250 characters
                        System.out.print("Enter your message: ");
                        String messageText = myInput.nextLine();

                        while (messageText.trim().isEmpty() || messageText.length() > 250) {
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

                        Message currentMessage = new Message(messageID, messagesEntered, firstName, recipient, messageText);

                        System.out.println();
                        System.out.println(currentMessage.messageIDCreated());

                        // Keep asking until a valid choice is entered
                        System.out.println();
                        System.out.println("---------- MESSAGE OPTIONS ----------");
                        System.out.println("1. Send Message");
                        System.out.println("2. Disregard Message");
                        System.out.println("3. Store Message");
                        System.out.println("-------------------------------------");
                        System.out.print("What do you want to do with this message? ");

                        int action = readNumber(myInput);

                        while (action < 1 || action > 4) {
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

                            String deleteKey = myInput.nextLine().trim();

                            while (!deleteKey.equals("0")) {
                                System.out.println("Press 0 to delete the message.");
                                deleteKey = myInput.nextLine().trim();
                            }

                            System.out.println("Message deleted.");

                        } else {
                            currentMessage.setMessageStatus("Stored");
                            storedList.add(currentMessage);
                            currentMessage.StoreMessages(storedList);
                        }

                        // Show the total once all the messages have been entered
                        if (messagesEntered == totalToSend) {
                            System.out.println();

                            System.out.println("MESSAGE LIMIT REACHED");
                            System.out.println("Total number of messages sent: " + currentMessage.returnTotalMessages(sentList));
                        }
                    }
                }

            } else if (menuOption == 2) {
                System.out.println();
               displaySentMessages(sentList);

            } else if (menuOption == 3) {
                System.out.println();

                System.out.println("Total number of messages sent: " + sentList.size());
                System.out.println("Goodbye.");
                quit = true;

            } else {
                System.out.println();
                System.out.println("Invalid option. Choose 1, 2 or 3.");
            }
        }

        myInput.close();
    }

    // Shows the QuickChat menu and returns the option the user chose
    public static int showMenu(Scanner myInput) {
        System.out.println();
        System.out.println("--------------- QUICKCHAT MENU -------------");
        System.out.println("1) Send Messages");
        System.out.println("2) Show recently sent messages");
        System.out.println("3) Quit");
        System.out.println("----------------------------------------------");
        System.out.print("Choose an option: ");

        return readNumber(myInput);
    }

    // Reads a whole number and keeps asking until a number is entered
    public static int readNumber(Scanner myInput) {
        String line = myInput.nextLine().trim();

        while (!line.matches("-?[0-9]{1,9}")) {
            System.out.print("Please enter a number: ");
            line = myInput.nextLine().trim();
        }

        return Integer.parseInt(line);
    }
    
    
    // Part 3 - Display all recently sent messages
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

}
