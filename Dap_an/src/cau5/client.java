package cau5;

import java.net.*;
import java.io.*;
import java.util.*;

public class client {
    public static void main(String[] args) {
        try {
            // Create UDP socket
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            
            // Prepare initial message
            String studentCode = "B21DCCN632"; // Replace with your student code
            String qCode = "piSQf5ct";
            String message = ";" + studentCode + ";" + qCode;
            
            // Send initial message
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, 
                                                         sendData.length, 
                                                         serverAddress, 
                                                         2208);
            clientSocket.send(sendPacket);
            
            // Receive response from server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, 
                                                            receiveData.length);
            clientSocket.receive(receivePacket);
            
            // Process received data
            String receivedMessage = new String(receivePacket.getData(), 
                                             0, 
                                             receivePacket.getLength());
            String[] parts = receivedMessage.split(";");
            String requestId = parts[0];
            String strInput = parts[1];
            
            // Process the string
            String processedString = processString(strInput);
            
            // Send processed result
            String resultMessage = requestId + ";" + processedString;
            sendData = resultMessage.getBytes();
            sendPacket = new DatagramPacket(sendData, 
                                          sendData.length, 
                                          serverAddress, 
                                          2208);
            clientSocket.send(sendPacket);
            
            // Close socket
            clientSocket.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static String processString(String input) {
        StringBuilder result = new StringBuilder();
        Set<Character> seen = new HashSet<>();
        
        for (char c : input.toCharArray()) {
            // Skip special characters and numbers
            if (!Character.isLetterOrDigit(c) || Character.isDigit(c)) {
                continue;
            }
            
            // Add only if not seen before
            if (!seen.contains(c)) {
                result.append(c);
                seen.add(c);
            }
        }
        
        return result.toString();
    }
}