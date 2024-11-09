/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cau3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket sc = new Socket("localhost",5555);
        BufferedReader br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sc.getOutputStream()));
        
        String code = "MSV;qcode";
        bw.write(code);
        bw.newLine();
        bw.flush();
        System.out.println("Sending code to server: "+code);
        
        String input = br.readLine();
        String filter_input = "";
        for (String i : input.split(" ")){
            filter_input += i;
        }
        System.out.println("Received input: "+input);
        
        Map<Character,Integer> mp = new Hashtable<>();
        for (int i = 0;i< filter_input.length();i++){
            mp.put(filter_input.charAt(i), mp.getOrDefault(filter_input.charAt(i), 0) +1);
        }
        String res = "";
        for (Character c : mp.keySet()){
            int val = mp.get(c);
            if (val >1){
                res += c+":"+val+",";
            }
        }
        res = res.substring(0, res.length()-1);
        System.out.println("Sendidng to server: "+res);
        bw.write(res);
        bw.flush();
        bw.close();
        br.close();
        sc.close();
    }
}
