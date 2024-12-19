/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cau11_v2;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/*
[Mã câu hỏi (qCode): baNyILKE].  Một chương trình server cho phép kết nối qua TCP tại cổng 2207 
(hỗ trợ thời gian liên lạc tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu là xây dựng chương trình 
client tương tác với server bằng các byte stream (DataInputStream/DataOutputStream) để trao đổi 
thông tin theo trình tự sau:

a. Gửi một chuỗi chứa mã sinh viên và mã câu hỏi ở định dạng "studentCode;qCode". Ví dụ: "B10DCCN000;A1B2C3D4".

b. Nhận từ server một mảng chứa n số nguyên, với n được gửi từ máy chủ. Ví dụ: Server gửi mảng [5, 9, 3, 6, 8].

c. Tính tổng, trung bình cộng, và phương sai của mảng. Gửi kết quả lần lượt lên server dưới dạng số
nguyên và float. Ví dụ, gửi lên lần lượt: 31, 6.2, 4.5599995.

d. Đóng kết nối và kết thúc chương trình.

 */
public class Cau11_v2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String code = "B21DCCN632;baNyILKE";
        Socket sc = new Socket("203.162.10.109", 2207);
        DataInputStream in =  new DataInputStream(sc.getInputStream());
        DataOutputStream out = new DataOutputStream(sc.getOutputStream());
        
        out.writeUTF(code);
        out.flush();
        
        int[] nums = in.readInt();
        
    }
    
}
