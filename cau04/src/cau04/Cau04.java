package cau04;



import java.io.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.ParseException;
import TCP.Customer;

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
    public static void main(String[] args) {
        String serverAddress = "203.162.10.109";
        int serverPort = 2209;
        String studentCode = "B21DCCN632";
        String qCode = "auzMOkdP";

        try (Socket socket = new Socket(serverAddress, serverPort)) {
            socket.setSoTimeout(5000);

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            
            // Step 1: Send student code and question code
            out.writeObject(studentCode + ";" + qCode);

            // Step 2: Receive Customer918 object
            Customer customer = (Customer) in.readObject(); 

            // Step 3: Format the data
            String formattedName = formatName(customer.getName());
            String formattedDate = formatDate(customer.getDayOfBirth());
            String generatedUsername = generateUserName(customer.getName());

            // Update customer object
            customer.setName(formattedName);
            customer.setDayOfBirth(formattedDate);
            customer.setUserName(generatedUsername);

            // Step 4: Send modified object back to server
            out.writeObject(customer);

        } catch (IOException | ClassNotFoundException  e) {
            e.printStackTrace();
        }
    }
    private static String generateUserName(String name){
        String[] words = name.toLowerCase().split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i =0 ;i<words.length-1;i++){
            sb.append(words[i].charAt(0));
        }
        sb.append(words[words.length-1]);
        return sb.toString();
    }
    private static String formatName(String name){
        String[] words = name.split(" ");
        StringBuilder sb = new StringBuilder();
        sb.append(words[words.length-1].toUpperCase() +",");
        for (int i = 0;i< words.length-1;i++){
            sb.append(" "+words[i].toUpperCase().charAt(0))
                    .append(words[i].toLowerCase().substring(1));
        }
        return sb.toString();
    }
    private static String formatDate(String dob){
        String[] list = dob.split("-");
        StringBuilder sb = new StringBuilder();
        sb.append(list[1]+"/");
        sb.append(list[0]+"/");
        sb.append(list[2]);
        return sb.toString();
    }
    
}
