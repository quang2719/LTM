
import java.io.IOException;
import java.net.Socket;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("google.com", 80);
        System.out.println(client);
    }
}
