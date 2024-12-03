package cau6;

import java.net.*;
import java.io.*;

public class client {
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("203.162.10.109", 2206);
            clientSocket.setSoTimeout(5000);

            InputStream in = clientSocket.getInputStream();
            OutputStream out = clientSocket.getOutputStream();
            
            String studentCode = "B21DCCN632";
            String qCode = "O1UMX4aS";
            String message = studentCode + ";" + qCode;
            out.write(message.getBytes());
            out.flush();
            
            byte[] buffer = new byte[2048]; // Increased buffer size
            int bytesRead = in.read(buffer);
            String received = new String(buffer, 0, bytesRead);
            
            String[] numbers = received.split(",");
            long[] nums = new long[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                nums[i] = Long.parseLong(numbers[i].trim());
            }
            
            long[] result = findSecondLargest(nums);
            String response = result[0] + "," + result[1];
            out.write(response.getBytes());
            out.flush();
            
            clientSocket.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
private static long[] findSecondLargest(long[] nums) {
    if (nums == null || nums.length < 2) {
        return new long[]{-1, -1}; // Handle invalid input
    }

    long largest = Long.MIN_VALUE;
    long secondLargest = Long.MIN_VALUE;
    int largestPos = -1;
    int secondLargestPos = -1;
    
    // First pass: find largest
    for (int i = 0; i < nums.length; i++) {
        if (nums[i] > largest) {
            secondLargest = largest;
            secondLargestPos = largestPos;
            largest = nums[i];
            largestPos = i;
        } else if (nums[i] > secondLargest && nums[i] != largest) {
            secondLargest = nums[i];
            secondLargestPos = i;
        }
    }
    
    // Check if second largest was found
    if (secondLargestPos == -1) {
        return new long[]{-1, -1}; // No second largest exists (all numbers are equal)
    }
    
    return new long[]{secondLargest, secondLargestPos}; // Adding 1 for 1-based indexing
}
}