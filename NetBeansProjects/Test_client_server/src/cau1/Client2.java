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
        String code = "B21;hKy3HKYZ";
        bw.write(code);
        bw.newLine();
        bw.flush();
        String s= br.readLine();
        System.out.println(s);
        String []a =s.trim().split("\\,");
        ArrayList<String>res = new ArrayList<>();
        String q = "";
        for (String x:a){
            if(x.endsWith(".edu")) res.add(x);
        }
        for(int i = 0; i<res.size()-1;i++) q+=res.get(i) +",";
        q+= res.get(res.size()-1);
        bw.write(q);
        System.out.println(q);
        bw.newLine();
        bw.flush();
        br.close();
        bw.close();
        sc.close();
      
    }
}
