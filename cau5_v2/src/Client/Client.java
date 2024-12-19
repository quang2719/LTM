/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;

//[Mã câu hỏi (qCode): piSQf5ct].  [Loại bỏ ký tự đặc biệt, số, trùng và giữ nguyên thứ tự xuất hiện]

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

//Một chương trình server cho phép kết nối qua giao thức UDP tại cổng 2208 . Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản dưới đây:
//a.	Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN001;06D6800D"
//b.	Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;strInput"
//•	requestId là chuỗi ngẫu nhiên duy nhất
//•	strInput là chuỗi thông điệp cần xử lý
//c.	Thực hiện loại bỏ ký tự đặc biệt, số, ký tự trùng và giữ nguyên thứ tự xuất hiện của chúng. Gửi thông điệp lên server theo định dạng "requestId;strOutput", trong đó strOutput là chuỗi đã được xử lý ở trên
//d.	Đóng socket và kết thúc chương trình.

public class Client{
    public static void main(String[] args) throws UnknownHostException, SocketException, IOException {
        int port = 2208;
        String address = "203.162.10.109";
        String code = ";B21DCCN632;piSQf5ct";
        
        DatagramSocket client = new DatagramSocket();
        InetAddress sever = InetAddress.getByName(address);
        
        byte[] data = code.getBytes();
        DatagramPacket dataPacket = new DatagramPacket(data,data.length,sever, port);
        client.send(dataPacket);
        
        // nhan code
        
        byte[] data_raw = new byte[1024];
        DatagramPacket data_raw_package = new DatagramPacket(data_raw,data_raw.length, sever,port);
        client.receive(data_raw_package);
        
        // xuly code
        String s = new String(data_raw_package.getData(),0,data_raw_package.getLength());
        String[] words = s.split(";");
        String id = words[0];
        String input = words[1];
        
        StringBuilder sb = new StringBuilder();
        for(char c: input.toCharArray()){
            if(Character.isAlphabetic(c)){
                if (sb.indexOf(Character.toString(c))==-1){
                    sb.append(Character.toString(c));
                }
            }
        }
        String res = id +";"+sb.toString();
        
        byte[] res_data = res.getBytes();
        DatagramPacket res_pack = new DatagramPacket(res_data,res_data.length,sever, port);
        client.send(res_pack);
        client.close();
    }
}

