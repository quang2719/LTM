
package Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;



//[Mã câu hỏi (qCode): piSQf5ct].  [Loại bỏ ký tự đặc biệt, số, trùng và giữ nguyên thứ tự xuất hiện]
//Một chương trình server cho phép kết nối qua giao thức UDP tại cổng 2208 . Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản dưới đây:
//a.	Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN001;06D6800D"
//b.	Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;strInput"
//•	requestId là chuỗi ngẫu nhiên duy nhất
//•	strInput là chuỗi thông điệp cần xử lý
//c.	Thực hiện loại bỏ ký tự đặc biệt, số, ký tự trùng và giữ nguyên thứ tự xuất hiện của chúng. Gửi thông điệp lên server theo định dạng "requestId;strOutput", trong đó strOutput là chuỗi đã được xử lý ở trên
//d.	Đóng socket và kết thúc chương trình.
public class Client {
    public static void main(String[] args) {
        try{
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress sever = InetAddress.getByName("203.162.10.109");
            int port = 2208;
            String message = ";B21DCCN632;piSQf5ct";
            
            // gui
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, sever, port);
            clientSocket.send(sendPacket);

            //nhan
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String receiveMessage = new String(receivePacket.getData(),0,receivePacket.getLength());

            System.out.println("string: "+ receiveMessage);

            //xu ly chuoi nhan duoc
            String[] parts = receiveMessage.split(";");
            String req = parts[0];
            String input = parts[1];

            StringBuilder sb = new StringBuilder();
            for (char c: input.toCharArray()){
                if(Character.isAlphabetic(c)){
                    if (sb.indexOf(Character.toString(c)) == -1){
                        sb.append(Character.toString(c));
                    }
                }
            }
            String res = sb.toString();

            // gui kq
            String resultMessage = req+";"+res;

            System.out.println("return "+resultMessage);
            
            
            sendData = resultMessage.getBytes();
            sendPacket = new DatagramPacket(sendData, sendData.length, sever, port);
            clientSocket.send(sendPacket);

        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
}
