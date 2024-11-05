/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cau1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Client2 {
    public static void main(String[] args)throws IOException {
        Socket sc = new Socket("localhost",5555); // Thay local host = ip 
        
        BufferedReader br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sc.getOutputStream()));
        
        // gui chuoi ma sv
        String code = "B21;hKy3HKYZ";
        bw.write(code);
        bw.newLine(); // ghi dòng mới để biết dữ liệu đã kết thúc (đọc dl theo dòng)
        bw.flush(); // đảm bảo dữ liệu dc gửi luôn 
        // nhan ds tu server
        String s = br.readLine();
        System.out.println(s);
        
        String [] a = s.split(", ");
        
        ArrayList<String> res = new ArrayList<>();
        String q = "";
        for (String x:a){
            if(x.endsWith(".edu")) q+=x+',';
        }
        q = q.substring(0,q.length()-1);
        bw.write(q);
        System.out.println(q);
        bw.newLine();
        bw.flush();
        br.close();
        bw.close();
        sc.close();
      
    }
}
