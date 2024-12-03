
package cau1;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5555);

        // Tạo luồng đọc/ghi
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        // Gửi mã sinh viên và mã câu hỏi
        String code = "B21DCCN792;m81LDpdo";
        bw.write(code);
        bw.newLine();
        bw.flush();

        // Nhận danh sách tên miền từ server
        String domains = br.readLine();
        System.out.println("Received domains: " + domains);

        // Lọc tên miền .edu
        String[] domainList = domains.split(", ");
        List<String> eduDomains = new ArrayList<>();
        for (String domain : domainList) {
            if (domain.endsWith(".edu")) {
                eduDomains.add(domain);
            }
        }

        // Gửi danh sách tên miền .edu về server
        String eduDomainString = String.join(", ", eduDomains);
        bw.write(eduDomainString);
        bw.newLine();
        bw.flush();
        System.out.println("Sent .edu domains: " + eduDomainString);

        // Đóng kết nối
        br.close();
        bw.close();
        socket.close();
    }
}
