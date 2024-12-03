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
import java.util.List;

public class Client2 {
    public static void main(String[] args)throws IOException {
        Socket sc = new Socket("localhost",5555);
        
        BufferedReader br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sc.getOutputStream()));
        
        String code = "B21DCCN632;m81LDpdo";
        bw.write(code);
        bw.newLine();
        bw.flush();
        
        String input = br.readLine();
        List<String> email = new ArrayList<>();
        String[] sentences = input.split(", ");
        for (String sentence : sentences){
            if (sentence.endsWith(".edu")){
                email.add(sentence);
            }
        }
        String res = "";
        for(String e:email){
            res += e +", ";
        }
        res = res.substring(0,res.length()-2);
        bw.write(res);
        bw.newLine();
        bw.flush();
        
        bw.close();
        br.close();
        sc.close();
    }
}

//
//Ví dụ: giHgWHwkLf0Rd0.io, I7jpjuRw13D.io, wXf6GP3KP.vn, MdpIzhxDVtTFTF.edu, TUHuMfn25chmw.vn, HHjE9.com, 4hJld2m2yiweto.vn, y2L4SQwH.vn, s2aUrZGdzS.com, 4hXfJe9giAA.edu
//c.	Tìm kiếm các tên miền .edu và gửi lên server
