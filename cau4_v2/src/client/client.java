/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import TCP.Customer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.*;
import java.net.Socket;

/**
 *
 * @author Admin
 */
public class client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket sc = new Socket("203.162.10.109",2209);
        ObjectInputStream in = new ObjectInputStream(sc.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(sc.getOutputStream());
        
        String qCode = "auzMOkdP";
        String sCode = "B21DCCN632";
        out.writeObject(sCode +";"+qCode);
        System.out.println("send code: " + sCode +";"+qCode);
        
        
        
        Customer cus = (Customer) in.readObject();
        System.out.println("Earn object: "+ cus.toString());
        
        cus.setUserName(userNameFormat(cus.getName()));
        cus.setName(nameFormat(cus.getName()));
        cus.setDayOfBirth(dateFormat(cus.getDayOfBirth()));
        
        System.out.println("formated object: "+cus.toString());
        
        out.writeObject(cus);
        in.close();
        out.close();
        sc.close();
        
    }
    private static String nameFormat(String s){
        String[] words = s.toLowerCase().split(" ");
        StringBuilder sb = new StringBuilder();
        
        sb.append(words[words.length-1].toUpperCase() + ",");
        for(int i = 0; i < words.length-1;i++){
            sb.append(" ")
            .append(words[i].toUpperCase().charAt(0))
            .append(words[i].substring(1));
        }
        return sb.toString();
    }
    private static String userNameFormat(String s){
        String[] words = s.toLowerCase().split(" ");
        StringBuilder sb = new StringBuilder();
        
        for(int i =0;i<words.length-1;i++){
            sb.append(words[i].charAt(0));
        }
        sb.append(words[words.length-1]);
        return sb.toString();
    }
    private static String dateFormat(String date){
        String[] words = date.split("-");
        StringBuilder sb = new StringBuilder();
        sb.append(words[1]+"/");
        sb.append(words[0]+"/");
        sb.append(words[2]);
        return sb.toString();
    }
            
}




//[Mã câu hỏi (qCode): auzMOkdP].  Thông tin khách hàng cần thay đổi định dạng lại cho phù hợp với khu vực, cụ thể:
//a.	Tên khách hàng cần được chuẩn hóa theo định dạng mới. Ví dụ: nguyen van hai duong -> DUONG, Nguyen Van Hai
//b.	Ngày sinh của khách hàng hiện đang ở dạng mm-dd-yyyy, cần được chuyển thành định dạng dd/mm/yyyy. Ví dụ: 10-11-2012 -> 11/10/2012
//c.	Tài khoản khách hàng là các chữ cái in thường được sinh tự động từ họ tên khách hàng. Ví dụ: nguyen van hai duong -> nvhduong
//
//Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2209 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng các luồng đối tượng (ObjectInputStream / ObjectOutputStream) thực hiện gửi/nhận đối tượng khách hàng và chuẩn hóa. Cụ thể:
//a.	Đối tượng trao đổi là thể hiện của lớp Customer được mô tả như sau
//      •	Tên đầy đủ của lớp: TCP.Customer
//      •	Các thuộc tính: id int, code String, name String, dayOfBirth String, userName String
//      •	Hàm khởi tạo đầy đủ các thuộc tính được liệt kê ở trên
//      •	Trường dữ liệu: private static final long serialVersionUID = 20170711L; 
//b.	Tương tác với server theo kịch bản dưới đây:
//	1) Gửi đối tượng là một chuỗi gồm mã sinh viên và mã câu hỏi ở định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;F2DA54F3"
//	2) Nhận một đối tượng là thể hiện của lớp Customer từ server với các thông tin đã được thiết lập
//	3) Thay đổi định dạng theo các yêu cầu ở trên và gán vào các thuộc tính tương ứng.  Gửi đối tượng đã được sửa đổi lên server
//	4) Đóng socket và kết thúc chương trình.