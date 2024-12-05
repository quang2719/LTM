package cau11;

import java.io.*;
import java.net.*;
import java.util.*;

public class client {
    public static void main(String[] args) {
        String host = "203.162.10.109"; // Thay bằng địa chỉ IP của server nếu cần
        int port = 2207;

        try (Socket socket = new Socket(host, port)) {
            socket.setSoTimeout(5000); // Thiết lập thời gian chờ tối đa 5 giây
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            // Bước a: Gửi chuỗi "studentCode;qCode"
            String studentCode = "B21DCCN632";
            String qCode = "baNyILKE";
            String request = studentCode + ";" + qCode;
            dos.writeUTF(request);
            dos.flush();

            // Bước b: Nhận mảng số nguyên từ server
            int n = dis.readInt(); // Đọc số lượng phần tử
            int[] numbers = new int[n];
            for (int i = 0; i < n; i++) {
                numbers[i] = dis.readInt(); // Đọc từng phần tử
            }

            System.out.println("Received array from server: " + Arrays.toString(numbers));

            // Bước c: Tính toán tổng, trung bình cộng và phương sai
            int sum = Arrays.stream(numbers).sum();
            float average = (float) sum / n;
            float variance = 0;
            for (int num : numbers) {
                variance += (num - average) * (num - average);
            }
            variance /= n;

            System.out.println("Sum: " + sum);
            System.out.println("Average: " + average);
            System.out.println("Variance: " + variance);

            // Gửi kết quả lên server
            dos.writeInt(sum); // Gửi tổng
            dos.writeFloat(average); // Gửi trung bình cộng
            dos.writeFloat(variance); // Gửi phương sai
            dos.flush();

            // Đóng kết nối
            System.out.println("Results sent to server. Closing connection.");

        } catch (SocketTimeoutException e) {
            System.err.println("Connection timed out!");
        } catch (IOException e) {
            System.err.println("I/O Error: " + e.getMessage());
        }
    }
}
