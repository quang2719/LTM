/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import RMI.CharacterService;
import java.rmi.NotBoundException;
import java.rmi.registry.*;
import java.rmi.RemoteException;

/**
 *
 * @author Admin
 */
public class Character {
       // Phương thức chuyển đổi chuỗi La Mã thành số thập phân
    public static int romanToDecimal(String roman) {
        int result = 0;
        int prevValue = 0;

        // Duyệt từng ký tự trong chuỗi La Mã từ phải sang trái
        for (int i = roman.length() - 1; i >= 0; i--) {
            char c = roman.charAt(i);
            int value = romanCharToValue(c);

            // Nếu ký tự phía sau có giá trị lớn hơn hoặc bằng ký tự phía trước,
            // cộng giá trị của ký tự vào kết quả
            if (value >= prevValue) {
                result += value;
            } else {
                result -= value;
            }

            prevValue = value;
        }
        
        return result;
    }

    // Phương thức chuyển một ký tự La Mã thành giá trị tương ứng
    public static int romanCharToValue(char c) {
        return switch (c) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            case 'D' -> 500;
            case 'M' -> 1000;
            default -> 0;
        };
    }

    public static void main(String[] args) throws NotBoundException {
        try {
            // Bước 1: Kết nối tới RMI Registry
            Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099); // Đảm bảo địa chỉ và port của RMI Registry chính xác
            CharacterService characterService = (CharacterService) registry.lookup("RMICharacterService");

            // Bước 2: Triệu gọi phương thức requestCharacter để nhận chuỗi La Mã
            String studentCode = "B21DCCN632"; // Mã sinh viên
            String qCode = "CSv4zu4f"; // Mã câu hỏi
            String romanString = characterService.requestCharacter(studentCode, qCode); // Nhận chuỗi La Mã từ server

            System.out.println("Received Roman numeral: " + romanString);

            // Bước 3: Chuyển đổi chuỗi La Mã thành số thập phân
            int decimalValue = romanToDecimal(romanString);
            System.out.println("Decimal value: " + decimalValue);

            // Bước 4: Triệu gọi phương thức submitCharacter để gửi số thập phân trở lại server
            characterService.submitCharacter(studentCode, qCode, String.valueOf(decimalValue));
            System.out.println("Decimal value submitted successfully.");

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
