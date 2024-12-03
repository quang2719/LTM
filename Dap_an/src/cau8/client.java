package cau8;

import java.io.*;
import java.net.*;
import java.util.*;

public class client {
    public static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
    
    public static List<Integer> generatePrimes(int n) {
        List<Integer> primes = new ArrayList<>();
        int num = 2;
        while (primes.size() < n) {
            if (isPrime(num)) {
                primes.add(num);
            }
            num++;
        }
        return primes;
    }

    public static void main(String[] args) {
        final String HOST = "203.162.10.109";
        final int PORT = 2207;
        
        try {
            // Create UDP socket
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName(HOST);
            
            // Prepare initial message
            String message = ";B21DCCN632;XYKJXgmx";
            byte[] sendData = message.getBytes();
            
            // Send message to server
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, PORT);
            socket.send(sendPacket);
            
            // Receive response from server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            
            // Process server response
            String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());
            String[] parts = serverResponse.split(";");
            String requestId = parts[0];
            String[] numbers = parts[1].split(",");
            int n = Integer.parseInt(numbers[0].trim());
            
            // Generate prime numbers
            List<Integer> primes = generatePrimes(n);
            
            // Prepare response
            StringBuilder response = new StringBuilder();
            response.append(requestId).append(";");
            for (int i = 0; i < primes.size(); i++) {
                response.append(primes.get(i));
                if (i < primes.size() - 1) {
                    response.append(",");
                }
            }
            
            // Send prime numbers to server
            sendData = response.toString().getBytes();
            sendPacket = new DatagramPacket(sendData, sendData.length, address, PORT);
            socket.send(sendPacket);
            
            // Close socket
            socket.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}