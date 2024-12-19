
import java.net.Socket;
import java.util.*;
import java.io.*;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/*
 [Mã câu hỏi (qCode): O1UMX4aS].  Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2206 (thời gian giao 
tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client tương tác tới server ở trên sử dụng các luồng byte (InputStream/OutputStream) để trao đổi thông tin theo thứ tự: 
a.	Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B16DCCN999;2B3A6510"
b.	Nhận dữ liệu từ server là một chuỗi các giá trị số nguyên được phân tách nhau bởi ký tự ",". Ví dụ: 1,3,9,19,33,20
c.	Tìm và gửi lên server giá trị lớn thứ hai cùng vị trí xuất hiện của nó trong chuỗi.Ví dụ: 20,5
d.	Đóng kết nối và kết thúc chương trình.
 */
public class client {
    public static void main(String[] args) throws IOException {
        Socket sc = new Socket("203.162.10.109",2206);
        String code = "B21DCCN632;O1UMX4aS";
        
        InputStream in = sc.getInputStream();
        OutputStream out = sc.getOutputStream();
        
        //gui
        out.write(code.getBytes());
        out.flush();
        
        //nhan
        byte[] data = new byte[1024];
        int byte_data = in.read(data);
        String s  = new String(data,0,byte_data);
        
        String[] words = s.split(",");
        long[] nums = new long[words.length];
        
        for(int i =0;i<nums.length;i++){
            nums[i] = Long.parseLong(words[i]);
        }
        long[] res = findSecondMax(nums);
        
        String text = res[0]+"."+res[1];
        out.write(text.getBytes());
        out.flush();
        
        
    }
    public static long[] findSecondMax(long[] nums){
        long max1 = Long.MIN_VALUE;
        long max2 = max1;
        int idm1 = -1;
        int idm2 = -1;
        
        for(int i =0;i<nums.length;i++){
            if (nums[i]>max1){
                max2 = max1;
                idm2 = idm1;
                max1 = nums[i];
                idm1 = i;
            }else if(nums[i]>max2){
                max2 = nums[i];
                idm2 = i;
            }
        }
        return new long[]{max2,idm2};
    }
}
