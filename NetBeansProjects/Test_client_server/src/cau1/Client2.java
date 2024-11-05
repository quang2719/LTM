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
        Socket sc = new Socket("localhost",5555);
        BufferedReader br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sc.getOutputStream()));
        
        // gui 1 chuoi gom "tdcode;qcode"
        String code = "studentCode;qcode";
        bw.write(code);
        bw.newLine();
        bw.flush();
        
        // nhan 1 chuoi gom list cac string
        String s = br.readLine();
        System.out.println("s");
        String res = "";
        for (String word: s.split(", ")) {
            if (word.endsWith(".edu")) res += word+',';
        }
            
        res = res.substring(0,res.length()-1);
        bw.write(res);
        System.out.println(res);
        bw.flush();
        bw.close();
        br.close();
        sc.close();
        
      
    }
}
