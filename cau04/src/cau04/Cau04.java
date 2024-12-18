/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cau04;

import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.net.Socket;
//import cau04.TCP.Customer918;

/**
 *
 * @author Admin
 */
public class Cau04 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
//        String scode = "B21DCCN632";
//        String qcode = "auzMOkdP";
//        int port = 2209;
//        Socket socket = new Socket("203.162.10.109", port);
//        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
//        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
//        
//        out.writeObject(scode + ";" + qcode);
//       
//        Customer918 cus = (Customer918) in.readObject();
            String name = "Nguyen Viet Quang";
            String fname = FormatName(name);
            System.out.println(fname);
        
        
    }
    private static String FormatName(String name){
        String[] words = name.toLowerCase().split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i =0 ;i<words.length-1;i++){
            sb.append(words[i].charAt(0));
        }
        sb.append(words[words.length-1]);
        return sb.toString();
    }
    
}
