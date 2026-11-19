/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.baneletpart1;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author nosip
 */
public class Part3Tests {

    private ArrayList<Message> sentMessages;
    private ArrayList<Message> storedMessages;
    private ArrayList<Message> disregardedMessages;
    private ArrayList<String> messageHashes;
    private ArrayList<String> messageIDs;

    private Message message1;
    private Message message2;
    private Message message3;
    private Message message4;
    private Message message5;

    @BeforeEach
    public void setUp() {

        sentMessages = new ArrayList<>();
        storedMessages = new ArrayList<>();
        disregardedMessages = new ArrayList<>();
        messageHashes = new ArrayList<>();
        messageIDs = new ArrayList<>();

        // TEST DATA MESSAGE 1
        // Recipient: +27834557896
        // Message: Did you get the cake?
        // Flag: Sent

        message1 = new Message(
                "1234567890",
                1,
                "Developer",
                "+27834557896",
                "Did you get the cake?"
        );

        message1.setMessageStatus("Sent");

        // TEST DATA MESSAGE 2
        // Recipient: +27838884567
        // Flag: Stored

        message2 = new Message(
                "2345678901",
                2,
                "Developer",
                "+27838884567",
                "Where are you? You are late! I have asked you to be on time."
        );

        message2.setMessageStatus("Stored");

        // TEST DATA MESSAGE 3
        // Recipient: +27834484567
        // Flag: Disregard

        message3 = new Message(
                "3456789012",
                3,
                "Developer",
                "+27834484567",
                "Yohoooo, I am at your gate."
        );

        message3.setMessageStatus("Disregard");

        // TEST DATA MESSAGE 4
        // Developer: 0838884567
        // Flag: Sent

        message4 = new Message(
                "0838884567",
                4,
                "Developer",
                "0838884567",
                "It is dinner time!"
        );

        message4.setMessageStatus("Sent");

        // TEST DATA MESSAGE 5
        // Recipient: +27838884567
        // Flag: Stored

        message5 = new Message(
                "5678901234",
                5,
                "Developer",
                "+27838884567",
                "Ok, I am leaving without you."
        );

        message5.setMessageStatus("Stored");

        // POPULATE SENT MESSAGES ARRAY

        sentMessages.add(message1);
        sentMessages.add(message4);

        // POPULATE STORED MESSAGES ARRAY

        storedMessages.add(message2);
        storedMessages.add(message5);

        // POPULATE DISREGARDED MESSAGES ARRAY

        disregardedMessages.add(message3);

        // POPULATE MESSAGE HASH ARRAY

        messageHashes.add(message1.getMessageHash());
        messageHashes.add(message2.getMessageHash());
        messageHashes.add(message3.getMessageHash());
        messageHashes.add(message4.getMessageHash());
        messageHashes.add(message5.getMessageHash());

        // POPULATE MESSAGE ID ARRAY

        messageIDs.add(message1.getMessageID());
        messageIDs.add(message2.getMessageID());
        messageIDs.add(message3.getMessageID());
        messageIDs.add(message4.getMessageID());
        messageIDs.add(message5.getMessageID());
    }

    // TEST 1: SENT MESSAGES ARRAY CORRECTLY POPULATED

    @Test
    public void testSentMessagesArray() {

        assertEquals(2, sentMessages.size());

        assertEquals(
                "Did you get the cake?",
                sentMessages.get(0).getMessage()
        );

        assertEquals(
                "It is dinner time!",
                sentMessages.get(1).getMessage()
        );
    }

    // TEST 2: DISPLAY THE LONGEST MESSAGE

    @Test
    public void testLongestMessage() {

        Message longestMessage = storedMessages.get(0);

        for (Message currentMessage : storedMessages) {

            if (currentMessage.getMessage().length()
                    > longestMessage.getMessage().length()) {

                longestMessage = currentMessage;
            }
        }

        assertEquals(
                "Where are you? You are late! I have asked you to be on time.",
                longestMessage.getMessage()
        );
    }

    // TEST 3: SEARCH FOR MESSAGE ID

    @Test
    public void testSearchMessageID() {

        String searchID = "0838884567";

        String result = "";

        for (Message currentMessage : sentMessages) {

            if (currentMessage.getMessageID().equals(searchID)) {

                result = currentMessage.getMessage();
                break;
            }
        }

        assertEquals(
                "It is dinner time!",
                result
        );
    }

    // TEST 4: SEARCH ALL MESSAGES FOR A PARTICULAR RECIPIENT

    @Test
    public void testSearchRecipient() {

        String searchNumber = "+27838884567";

        ArrayList<String> matchingMessages = new ArrayList<>();

        ArrayList<Message> allMessages = new ArrayList<>();

        allMessages.addAll(sentMessages);
        allMessages.addAll(storedMessages);

        for (Message currentMessage : allMessages) {

            if (currentMessage.getRecipient().equals(searchNumber)) {

                matchingMessages.add(currentMessage.getMessage());
            }
        }

        assertEquals(2, matchingMessages.size());

        assertTrue(matchingMessages.contains(
                "Where are you? You are late! I have asked you to be on time."
        ));

        assertTrue(matchingMessages.contains(
                "Ok, I am leaving without you."
        ));
    }

    // TEST 5: DELETE A MESSAGE USING MESSAGE HASH

    @Test
    public void testDeleteMessageByHash() {

        String searchHash = message2.getMessageHash();

        String deletedMessage = "";

        for (int i = 0; i < storedMessages.size(); i++) {

            Message currentMessage = storedMessages.get(i);

            if (currentMessage.getMessageHash()
                    .equalsIgnoreCase(searchHash)) {

                deletedMessage = currentMessage.getMessage();

                storedMessages.remove(i);

                break;
            }
        }

        assertEquals(
                "Where are you? You are late! I have asked you to be on time.",
                deletedMessage
        );

        assertEquals(1, storedMessages.size());

        assertFalse(storedMessages.contains(message2));
    }

    // TEST 6: DISPLAY REPORT

    @Test
    public void testDisplayReport() {

        String report = "";

        for (Message currentMessage : sentMessages) {

            report += "Message Hash: "
                    + currentMessage.getMessageHash();

            report += "\nRecipient: "
                    + currentMessage.getRecipient();

            report += "\nMessage: "
                    + currentMessage.getMessage();

            report += "\n\n";
        }

        assertTrue(report.contains(message1.getMessageHash()));
        assertTrue(report.contains(message4.getMessageHash()));

        assertTrue(report.contains("+27834557896"));
        assertTrue(report.contains("0838884567"));

        assertTrue(report.contains("Did you get the cake?"));
        assertTrue(report.contains("It is dinner time!"));
    }
}

    