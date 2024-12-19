/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cau7_udp_object;

import UDP.Customer;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.sound.midi.Receiver;
/*
[Mã câu hỏi (qCode): awS5eLtD].  Thông tin khách hàng được yêu cầu thay đổi định dạng lại cho phù hợp với khu vực, cụ thể:
a.	Tên khách hàng cần được chuẩn hóa theo định dạng mới. Ví dụ: nguyen van hai duong -> DUONG, Nguyen Van Hai
b.	Ngày sinh của khách hàng đang ở dạng mm-dd-yyyy, cần được chuyển thành định dạng dd/mm/yyyy. Ví dụ: 10-11-2012 -> 11/10/2012
c.	Tài khoản khách hàng được tạo từ các chữ cái in thường được sinh tự động từ họ tên khách hàng. Ví dụ: nguyen van hai duong -> nvhduong


Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2209. Yêu cầu là xây dựng một chương trình client giao tiếp với server theo mô tả sau:
a.	Đối tượng trao đổi là thể hiện của lớp UDP.Customer được mô tả như sau
•	Tên đầy đủ của lớp: UDP.Customer
•	Các thuộc tính: id String, code String, name String, , dayOfBirth String, userName String
•	Một Hàm khởi tạo với đầy đủ các thuộc tính được liệt kê ở trên
•	Trường dữ liệu: private static final long serialVersionUID = 20151107; 

b.	Client giao tiếp với server theo các bước
•       Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng “;studentCode;qCode”. Ví dụ: “;B15DCCN001;EE29C059”

•	Nhận thông điệp chứa: 08 byte đầu chứa chuỗi requestId, các byte còn lại chứa một đối tượng là thể hiện của lớp Customer từ server. 
Trong đó, các thuộc tính id, code, name,dayOfBirth đã được thiết lập sẵn.
•	Yêu cầu thay đổi thông tin các thuộc tính như yêu cầu ở trên và gửi lại đối tượng khách hàng đã được sửa đổi lên server với cấu trúc:
08 byte đầu chứa chuỗi requestId và các byte còn lại chứa đối tượng Customer đã được sửa đổi.
•	Đóng socket và kết thúc chương trình.
 */
public class Cau7_UDP_Object {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        // TODO code application logic here
        String code = ";B21DCCN632;awS5eLtD";
        
        DatagramSocket client = new DatagramSocket();
        InetAddress server = InetAddress.getByName("203.162.10.109");
        
        byte[] send_code = code.getBytes();
        DatagramPacket code_pack = new DatagramPacket(send_code, 0, send_code.length, server,2209);
        client.send(code_pack);
        
        // get 8 byte
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(
                receiveData,0,receiveData.length, server,2209);
        client.receive(receivePacket);
        
        
        byte[] data = receivePacket.getData();
        String id = new String(data,0,8);
        
        ByteArrayInputStream bas = n
        
                
        
    }
    
}
