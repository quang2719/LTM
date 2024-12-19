/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cau08_udp_datatype;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
[Mã câu hỏi (qCode): XYKJXgmx].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng
2207. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:

a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định 
dạng ";studentCode;qCode". Ví dụ: ";B15DCCN009;F3E8B2D4".

b. Nhận thông điệp là một chuỗi từ server theo 
định dạng "requestId;n, n", với:
--- requestId là chuỗi ngẫu nhiên duy nhất.
--- n là một số nguyên ngẫu nhiên ≤ 100.

c. Tính và gửi về server danh sách n số nguyên tố đầu
tiên theo định dạng "requestId;p1,p2,...,pk", trong đó p1,p2,...,pk là các số nguyên tố.

d. Đóng socket và kết thúc chương trình.
 */
public class Cau08_UDP_DataType {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String code = ";B21DCCN632;XYKJXgmx";
        
        DatagramSocket sc = new DatagramSocket();
        InetAddress ina = InetAddress.getByName("203.162.10.109");
        
        byte[] byte_code = code.getBytes();
        DatagramPacket pack_code = new DatagramPacket(
                byte_code, 0,byte_code.length,ina, 2207);
        sc.send(pack_code);
        
        byte[] byte_input = new byte[1024];
        DatagramPacket pack_input = new DatagramPacket(byte_input, 0,byte_input.length,2209);
        
        
    }
    
}
