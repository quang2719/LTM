/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import RMI.ByteService;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import java.util.Arrays;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
public class Cau13 {
    // Chuyển đổi mảng byte thành chuỗi hex
    public static String byteArrayToHex(byte[] byteArray) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : byteArray) {
            hexString.append(String.format("%02x", b)); // Chuyển đổi từng byte thành 2 ký tự hex
        }
        return hexString.toString();
    }

    public static void main(String[] args) throws NotBoundException {
        try {
            // Bước 1: Kết nối đến RMI Registry
            Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);  // Đảm bảo RMI Registry đang chạy tại port 1099
            ByteService byteService = (ByteService) registry.lookup("RMIByteService");

            // Bước 2: Triệu gọi phương thức requestData để nhận dữ liệu nhị phân (byte array)
            String studentCode = "B21DCCN632";
            String qCode = "LypGzI3m";
            byte[] receivedData = byteService.requestData(studentCode, qCode);

            // In ra mảng byte nhận được (tùy chọn)
            System.out.println("Received byte data: " + Arrays.toString(receivedData));

            // Bước 3: Chuyển đổi mảng byte thành chuỗi hex
            String hexString = byteArrayToHex(receivedData);
            System.out.println("Hex representation: " + hexString);

            // Bước 4: Triệu gọi phương thức submitData để gửi lại dữ liệu hex dưới dạng byte array
            byte[] hexData = hexString.getBytes();  // Chuyển chuỗi hex thành mảng byte
            byteService.submitData(studentCode, qCode, hexData);
            System.out.println("Hex data submitted successfully.");

        } catch (RemoteException e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();  // In ra chi tiết stack trace

        }
    }
}