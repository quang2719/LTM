
// client.java
package cau7;

import UDP.Customer;
import java.io.*;
import java.net.*;

public class client {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("203.162.10.109");
            
            String message = ";B21DCCN632;awS5eLtD";
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, 2209);
            socket.send(sendPacket);
            
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            
            byte[] data = receivePacket.getData();
            String requestId = new String(data, 0, 8);
            
            ByteArrayInputStream bis = new ByteArrayInputStream(data, 8, receivePacket.getLength() - 8);
            ObjectInputStream ois = new ObjectInputStream(bis);
            Customer customer = (Customer) ois.readObject();
            
            // Update customer data with corrected formatting
            customer.setName(formatName(customer.getName()));
            customer.setDayOfBirth(formatDate(customer.getDayOfBirth()));
            customer.setUserName(generateUsername(customer.getName()));
            
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bos.write(requestId.getBytes());
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(customer);
            
            byte[] modifiedData = bos.toByteArray();
            DatagramPacket modifiedPacket = new DatagramPacket(modifiedData, modifiedData.length, address, 2209);
            socket.send(modifiedPacket);
            
            socket.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static String formatName(String name) {
        String[] parts = name.split(" ");
        String lastName = parts[parts.length - 1].toUpperCase();
        
        StringBuilder otherNames = new StringBuilder();
        for (int i = 0; i < parts.length - 1; i++) {
            if (!parts[i].isEmpty()) {
                otherNames.append(capitalizeWord(parts[i])).append(" ");
            }
        }
        
        return lastName + ", " + otherNames.toString().trim();
    }
    
    private static String capitalizeWord(String word) {
        if (word == null || word.isEmpty()) return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }
    
    private static String formatDate(String date) {
        String[] parts = date.split("-");
        if (parts.length != 3) return date;
        return String.format("%s/%s/%s", parts[1], parts[0], parts[2]);
    }
    
    private static String generateUsername(String name) {
        String[] parts = name.toLowerCase().split(",")[1].trim().split(" ");
        StringBuilder username = new StringBuilder();
        
        // Add first letter of each part except last
        for (int i = 0; i < parts.length; i++) {
            username.append(parts[i].charAt(0));
        }
        
        // Add last name (which is before the comma)
        username.append(name.toLowerCase().split(",")[0].trim());
        
        return username.toString().toLowerCase();
    }
}