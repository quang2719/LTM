/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cau2;

/**
 *
 * @author Admin
 */
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5555);
        System.out.println("Server is running on port 5555");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            // Luồng đọc và ghi dữ liệu từ/đến client
            DataInputStream din = new DataInputStream(socket.getInputStream());
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());

            // Nhận mã sinh viên và mã câu hỏi từ client
            String clientCode = din.readUTF();
            System.out.println("Received: " + clientCode);

            // Tạo một chuỗi các số nguyên ngẫu nhiên
            String numbers = generateRandomNumbers();
            dout.writeUTF(numbers);
            dout.flush();
            System.out.println("Sent numbers to client: " + numbers);

            // Nhận chuỗi sắp xếp từ client
            String sortedNumbers = din.readUTF();
            System.out.println("Received sorted numbers from client: " + sortedNumbers);

            // Đóng kết nối
            din.close();
            dout.close();
            socket.close();
        }
    }

    // Phương thức tạo chuỗi ngẫu nhiên các số nguyên
    private static String generateRandomNumbers() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            int num = random.nextInt(20) + 1;
            sb.append(num).append(",");
        }
        sb.append(random.nextInt(20) + 1); // Thêm số cuối mà không có dấu phẩy
        return sb.toString();
    }
}

