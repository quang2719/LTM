/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ongiuaki2;

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
public class OnGiuaKi2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("203.162.10.109", 2208);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String code = "B21DCCN792;hKy3HKYZ";
        bw.write(code);
        bw.newLine();
        bw.flush();
        String s = br.readLine();
        System.out.println(s);
        String []a = s.trim().split("\\, ");
        ArrayList<String>res = new ArrayList<>();
        String q = "";
        for(String x: a){
            if(x.endsWith(".edu")) res.add(x);
        }
        for(int i = 0;i<res.size() - 1;i++) q+=res.get(i) + ", ";
        q+=res.get(res.size() - 1);
        bw.write(q);
        System.out.println(q);
        bw.newLine();
        bw.flush();
        br.close();
        bw.close();
        socket.close();

    }
    
}
