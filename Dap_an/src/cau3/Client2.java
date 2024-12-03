package cau3;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Client2 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5555);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        // Gửi mã sinh viên và mã câu hỏi
        String studentCodeAndQCode = "B20DCCN999;ABCDEF";
        bw.write(studentCodeAndQCode);
        bw.newLine();
        bw.flush();

        // Nhận chuỗi từ server
        String receivedString = br.readLine();
        System.out.println("Received from server: " + receivedString);

        // Xử lý chuỗi để tìm các ký tự xuất hiện nhiều hơn một lần
        String result = findDuplicateCharacters(receivedString);
        System.out.println("Sending result to server: " + result);

        // Gửi kết quả lại cho server
        bw.write(result);
        bw.newLine();
        bw.flush();

        // Đóng kết nối
        br.close();
        bw.close();
        socket.close();
    }

    private static String findDuplicateCharacters(String input) {
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : input.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        StringBuilder result = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            if (entry.getValue() > 1) {
                result.append(entry.getKey()).append(":").append(entry.getValue()).append(",");
            }
        }

        // Loại bỏ dấu phẩy cuối cùng
        if (result.length() > 0) {
            result.setLength(result.length() - 1);
        }

        return result.toString();
    }
}