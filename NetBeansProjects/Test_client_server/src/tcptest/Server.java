/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcptest;
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5555);
        System.out.println("Server is running on port 5555");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            // Tạo luồng đọc/ghi
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // Nhận mã sinh viên và mã câu hỏi từ client
            String clientCode = br.readLine();
            System.out.println("Received: " + clientCode);

            // Danh sách tên miền ngẫu nhiên
            String domains = "giHgWHwkLf0Rd0.io, I7jpjuRw13D.io, MdpIzhxDVtTFTF.edu, TUHuMfn25chmw.vn, HHjE9.com, y2L4SQwH.vn, s2aUrZGdzS.com, 4hXfJe9giAA.edu";
            bw.write(domains);
            bw.newLine();
            bw.flush();

            // Nhận lại danh sách tên miền .edu từ client
            String eduDomains = br.readLine();
            System.out.println("Received .edu domains: " + eduDomains);

            // Đóng kết nối
            br.close();
            bw.close();
            socket.close();
        }
    }
}
