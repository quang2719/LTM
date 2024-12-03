/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cau3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5555);
        System.out.println("Server is running on port 5555...");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // Nhận mã sinh viên và mã câu hỏi từ client
            String studentCodeAndQCode = br.readLine();
            System.out.println("Received from client: " + studentCodeAndQCode);

            // Chuỗi ký tự ngẫu nhiên để gửi đến client
            String randomString = generateRandomString();
            bw.write(randomString);
            bw.newLine();
            bw.flush();
            System.out.println("Sent random string to client: " + randomString);

            // Nhận kết quả từ client
            String result = br.readLine();
            System.out.println("Received result from client: " + result);

            // Đóng kết nối
            br.close();
            bw.close();
            socket.close();
        }
    }

    private static String generateRandomString() {
        String chars = "ABCDEFGHIJKLMNO PQRSTUVWX YZabcdefghij klmnopqr stuvwxyz0123 456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}