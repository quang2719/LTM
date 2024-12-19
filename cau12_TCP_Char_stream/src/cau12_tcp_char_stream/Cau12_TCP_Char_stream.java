/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cau12_tcp_char_stream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author Admin
 */
public class Cau12_TCP_Char_stream {
    public static void main(String[] args) {
        String host = "203.162.10.109"; // Địa chỉ server
        int port = 2208;

        try (Socket socket = new Socket(host, port)) {
            socket.setSoTimeout(5000); // Thiết lập thời gian chờ tối đa 5 giây
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Bước a: Gửi chuỗi "studentCode;qCode"
            String studentCode = "B21DCCN632";
            String qCode = "FvoPU4bP";
            writer.write(studentCode + ";" + qCode);
            writer.newLine();
            writer.flush();

            // Bước b: Nhận chuỗi ngẫu nhiên từ server
            String randomString = reader.readLine();

            // Bước c1: Tìm từ dài nhất trong chuỗi
            String[] words = randomString.split("\\s+");
            String longestWord = "";
            for (String word : words) {
                if (word.length() > longestWord.length()) {
                    longestWord = word;
                }
            }

            // Bước c2: Xác định vị trí bắt đầu của từ dài nhất
            int startIndex = randomString.indexOf(longestWord);

            // Bước d: Gửi kết quả lên server
            writer.write(longestWord);
            writer.newLine();
            writer.flush();
            writer.write(String.valueOf(startIndex));
            writer.newLine();
            writer.flush();

        } catch (IOException e) {
            // Xử lý ngoại lệ nếu cần thiết
        }
    }
}

