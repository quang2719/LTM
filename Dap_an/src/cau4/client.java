package cau4;

import java.io.*;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import TCP.Customer;

public class client {
    public static void main(String[] args) {
        String serverAddress = "203.162.10.109";
        int serverPort = 2209;
        String studentCode = "B21DCCN632";
        String qCode = "auzMOkdP";

        try (Socket socket = new Socket(serverAddress, serverPort)) {
            socket.setSoTimeout(5000);

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            
            // Step 1: Send student code and question code
            out.writeObject(studentCode + ";" + qCode);

            // Step 2: Receive Customer918 object
            Customer customer = (Customer) in.readObject();

            // Step 3: Format the data
            String formattedName = formatName(customer.getName());
            String formattedDate = formatDate(customer.getDayOfBirth());
            String generatedUsername = generateUserName(customer.getName());

            // Update customer object
            customer.setName(formattedName);
            customer.setDayOfBirth(formattedDate);
            customer.setUserName(generatedUsername);

            // Step 4: Send modified object back to server
            out.writeObject(customer);

        } catch (IOException | ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static String formatName(String name) {
        String[] words = name.toLowerCase().split(" ");
        StringBuilder result = new StringBuilder();
        
        // Get last word as surname in uppercase
        String surname = words[words.length - 1].toUpperCase();
        result.append(surname).append(", ");
        
        // Format rest of the name
        for (int i = 0; i < words.length - 1; i++) {
            if (words[i].length() > 0) {
                result.append(Character.toUpperCase(words[i].charAt(0)))
                      .append(words[i].substring(1))
                      .append(" ");
            }
        }
        return result.toString().trim();
    }

    private static String formatDate(String date) throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("MM-dd-yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date parsedDate = inputFormat.parse(date);
        return outputFormat.format(parsedDate);
    }

    private static String generateUserName(String name) {
        String[] words = name.toLowerCase().split(" ");
        StringBuilder username = new StringBuilder();
        
        // Add first letter of each word except the last
        for (int i = 0; i < words.length - 1; i++) {
            if (words[i].length() > 0) {
                username.append(words[i].charAt(0));
            }
        }
        
        // Add the last word (surname) completely
        if (words.length > 0) {
            username.append(words[words.length - 1]);
        }
        
        return username.toString().toLowerCase();
    }
}