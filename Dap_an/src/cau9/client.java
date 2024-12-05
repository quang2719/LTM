package cau9;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import RMI.CharacterService;

public class Client {
    public static void main(String[] args) {
        String studentCode = "B21DCCN632";
        String qCode = "CSv4zu4f";

        try {
            // Kết nối tới RMI Registry
            Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);

            // Lấy đối tượng từ xa
            CharacterService service = (CharacterService) registry.lookup("RMICharacterService");

            // Triệu gọi phương thức requestCharacter để nhận chuỗi La Mã
            String romanString = service.requestCharacter(studentCode, qCode);
            System.out.println("Chuỗi số La Mã nhận được từ server: " + romanString);

            // Chuyển đổi chuỗi La Mã sang số thập phân
            int decimalValue = romanToDecimal(romanString);
            System.out.println("Giá trị thập phân: " + decimalValue);

            // Gửi số thập phân đã chuyển đổi trở lại server
            service.submitCharacter(studentCode, qCode, String.valueOf(decimalValue));
            System.out.println("Đã gửi giá trị thập phân trở lại server.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Hàm chuyển đổi chuỗi số La Mã sang số thập phân
    private static int romanToDecimal(String roman) {
        int[] values = new int[roman.length()];
        for (int i = 0; i < roman.length(); i++) {
            switch (roman.charAt(i)) {
                case 'I': values[i] = 1; break;
                case 'V': values[i] = 5; break;
                case 'X': values[i] = 10; break;
                case 'L': values[i] = 50; break;
                case 'C': values[i] = 100; break;
                case 'D': values[i] = 500; break;
                case 'M': values[i] = 1000; break;
                default: throw new IllegalArgumentException("Ký tự La Mã không hợp lệ: " + roman.charAt(i));
            }
        }

        int decimal = 0;
        for (int i = 0; i < values.length - 1; i++) {
            if (values[i] < values[i + 1]) {
                decimal -= values[i];
            } else {
                decimal += values[i];
            }
        }
        return decimal + values[values.length - 1];
    }
}
