package cau2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class Client2 {
    public static void main(String[] args) throws IOException {
        Socket sc = new Socket("localhost",5555);
        DataInputStream d_in = new DataInputStream(sc.getInputStream());
        DataOutputStream d_out = new DataOutputStream(sc.getOutputStream());
        
        String code = "D21DCCN632,qCode";
        d_out.writeUTF(code);
        d_out.flush();
        System.out.println("Send code to server:"+code);
        
        String input = d_in.readUTF();
        String[] nums = input.split(",");
        System.out.println("Received Input: "+input);
        
        List<Integer> even = new ArrayList<>();
        List<Integer> odd = new ArrayList<>();
        
        for (String num :nums){
            int n = Integer.parseInt(num);
            if (n%2==0){
                even.add(n);
            }else{
                odd.add(n);
            }
        }
        
        Collections.sort(odd);
        Collections.sort(even);
        
        String res = even.toString() +";"+odd.toString();
        d_out.writeUTF(res);
        d_out.flush();
        
        System.out.println("Send response to server "+ res);
        
        d_in.close();
        d_out.close();
        sc.close();
        
    }
}
