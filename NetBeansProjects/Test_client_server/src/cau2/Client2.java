package cau2;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class Client2 {
    public static void main(String[] args) throws IOException {
        Socket sc = new Socket("localhost",5555);
        
        DataInputStream br = new DataInputStream(sc.getInputStream());
        DataOutputStream bw = new DataOutputStream(sc.getOutputStream());
        
        String code = "B21;asd";
        bw.writeUTF(code);
        bw.flush();
        
        String input = br.readUTF();
        System.out.println("receive: "+ input);
        List<Integer> chan = new ArrayList<>();
        List<Integer> le = new ArrayList<>();
        
        for (String word : input.split(",")){
            int i = Integer.parseInt(word.trim());
            if (i%2==0) chan.add(i);
            else le.add(i);
        }
        Collections.sort(chan);
        Collections.sort(le);
        
        String formattedResult = chan.toString() + ";" + le.toString();
        System.out.println("Sent tAo server: "+ formattedResult);
        bw.writeUTF(formattedResult);
        bw.flush();
        bw.close();
        br.close();
        sc.close();
        
    }
}
