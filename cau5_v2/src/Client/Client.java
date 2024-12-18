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

public class Client {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        try{
            DatagramSocket client = new DatagramSocket();
            InetAddress server = InetAddress.getByName("203.162.10.109");
            int port = 2208;
            String mes1 = ";B21DCCN632;piSQf5ct";

            //send
            byte[] mes_data = mes1.getBytes();
            DatagramPacket mes_packet = new DatagramPacket(mes_data, mes_data.length,server,port);
            client.send(mes_packet);
            System.out.println("gui qcode thanh cong");

            //receive
            byte[] rec_data = new byte[1024];
            DatagramPacket rec_packet = new DatagramPacket(rec_data, rec_data.length,server,port);
            client.receive(rec_packet);
            String rec_mes = new String(rec_packet.getData(),0,rec_packet.getLength());
            System.out.println("nhan thanh cong: "+rec_mes);

            // handle string
            String[] messages = rec_mes.split(";");
            String req = messages[0];
            String input = messages[1];

            StringBuilder sb = new StringBuilder();
            for(char c: input.toCharArray()){
                if(Character.isAlphabetic(c)){
                    if(sb.indexOf(Character.toString(c)) == -1){
                        sb.append(Character.toString(c));
                    }
                }
            }
            String res = req +";" + sb.toString();

            // send again
            byte[] res_data = res.getBytes();
            DatagramPacket res_packet = new DatagramPacket(res_data, res_data.length,server,port);
            client.send(res_packet);

            System.out.println("gui thanh cong: "+res);
            client.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
