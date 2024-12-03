package cau2;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5555);
        DataInputStream din = new DataInputStream(socket.getInputStream());
        DataOutputStream dout = new DataOutputStream(socket.getOutputStream());

        // Gửi mã sinh viên và mã câu hỏi tới server
        String code = "B20DCCN999;ABCDEF";
        dout.writeUTF(code);
        dout.flush();

        // Nhận chuỗi các số nguyên từ server
        String numbers = din.readUTF();
        System.out.println("Received numbers from server: " + numbers);

        // Tách chuỗi thành danh sách các số nguyên
        List<Integer> evenNumbers = new ArrayList<>();
        List<Integer> oddNumbers = new ArrayList<>();
        for (String numStr : numbers.split(",")) {
            int num = Integer.parseInt(numStr.trim());
            if (num % 2 == 0) {
                evenNumbers.add(num);
            } else {
                oddNumbers.add(num);
            }
        }

        // Sắp xếp chẵn tăng dần và lẻ tăng dần
        Collections.sort(evenNumbers);
        Collections.sort(oddNumbers);

        // Chuyển đổi danh sách thành định dạng yêu cầu
        String formattedResult = evenNumbers.toString() + ";" + oddNumbers.toString();
        dout.writeUTF(formattedResult);
        dout.flush();
        System.out.println("Sent sorted numbers to server: " + formattedResult);

        // Đóng kết nối
        din.close();
        dout.close();
        socket.close();
    }
}
