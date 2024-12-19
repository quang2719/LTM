
import com.sun.source.tree.Scope;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;



//[Mã câu hỏi (qCode): O1UMX4aS].  Một chương trình server cho phép kết nối qua 
//giao thức TCP tại cổng 2206 (thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). 
//Yêu cầu là xây dựng một chương trình client tương tác tới server ở trên sử dụng 
//các luồng byte (InputStream/OutputStream) để trao đổi thông tin theo thứ tự: 

//a.	Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". 
//Ví dụ: "B16DCCN999;2B3A6510"

//b.	Nhận dữ liệu từ server là một chuỗi các giá trị số nguyên được phân tách
//nhau bởi ký tự ",". Ví dụ: 1,3,9,19,33,20

//c.	Tìm và gửi lên server giá trị lớn thứ hai cùng vị trí xuất hiện của nó 
//trong chuỗi.Ví dụ: 20,5

//d.	Đóng kết nối và kết thúc chương trình.


public class cau06 {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        String code = "B21DCCN632;O1UMX4aS";
        String address = "203.162.10.109";
        int port = 2206;
        
        Socket sc = new Socket(address, port);
        InputStream in = sc.getInputStream();
        OutputStream out = sc.getOutputStream();
        
        // gui
        out.write(code.getBytes());
        out.flush();
        
        //nhan
        byte[] input = new byte[1024];
        int byteRead = in.read(input);
        String received = new String(input,0,byteRead);
        
        // xuly
        String[] words = received.split(",");
        long[] nums = new long[words.length];
        for (int i =0;i<nums.length;i++){
            nums[i] = Long.parseLong(words[i]);
        }
        long[] res = findSecondLargest(nums);
        String result = res[0]+","+res[1];
        out.write(result.getBytes());
        out.flush();
        sc.close();
        
       
        
        
        
    }
    private static long[] findSecondLargest(long[] nums){
        long largest = Long.MIN_VALUE;
        long largest2 = largest;
        int largestId = -1;
        int largest2Id = -1;
        
        for(int i = 0;i< nums.length;i++){
            if (nums[i] > largest) {
                largest2 = largest;
                largest2Id = largestId;
                largest = nums[i];
                largestId = i;
            }else if(nums[i] > largest2){
                largest2 = nums[i];
                largest2Id = i;
            }
          
        }
        return new long[]{largest2,largest2Id};
    }
}
