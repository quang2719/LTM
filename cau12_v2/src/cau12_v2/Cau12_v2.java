/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cau12_v2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/*
[Mã câu hỏi (qCode): FvoPU4bP].  Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2208 
(hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu là xây dựng một chương trình client
thực hiện kết nối tới server và sử dụng luồng ký tự (BufferedWriter/BufferedReader) để trao đổi thông tin theo 
kịch bản sau:
a. Gửi một chuỗi gồm mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;A1B2C3D4".
b. Nhận từ server một chuỗi ngẫu nhiên.
c. Xử lý chuỗi đã nhận theo các bước sau:
    Bước 1: Tìm từ dài nhất trong trong chuỗi ngẫu nhiên (từ là chuỗi con phân tách bởi khoảng trắng).
    Bước 2: Xác định vị trí bắt đầu của từ dài nhất đó trong chuỗi ban đầu.
d. Gửi lần lượt hai giá trị lên server:
   - Từ dài nhất xuất hiện trong chuỗi.
   - Vị trí bắt đầu của từ trong chuỗi ban đầu.

e. Đóng kết nối và kết thúc chương trình.
 */
public class Cau12_v2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String code = "B21DCCN632;FvoPU4bP";
        Socket sc = new Socket("203.162.10.109", 2208);
        BufferedReader in = new BufferedReader(new InputStreamReader(sc.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(sc.getOutputStream()));
        
        out.write(code);
        out.newLine();
        out.flush();
        
        String line = in.readLine();
        String[] words = line.split(" ");
        String target = "";
        int id = -1;
        for (int i =0;i<words.length;i++){
            if (words[i].length() > target.length()){
                target = words[i];
                id = i;
            }
        }
        out.write(target);
        out.newLine();
        out.flush();
        out.write(id);
        out.newLine();
        out.flush();
        
        in.close();
        out.close();
        sc.close();
        
    }
    
}
