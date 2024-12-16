import RMI.CharacterService;
import org.json.JSONObject;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args) {
        String studentCode = "B21DCCN089";
        String qCode = "ufK2jSiW";

        try {
            // Kết nối tới RMI Registry Server
            Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099); // Thay "localhost" bằng địa chỉ server nếu cần
            CharacterService service = (CharacterService) registry.lookup("RMICharacterService");

            // Triệu gọi requestCharacter để lấy chuỗi JSON
            String jsonInput = service.requestCharacter(studentCode, qCode);
            System.out.println("Received JSON: " + jsonInput);

            // Phân tích và xử lý chuỗi JSON
            JSONObject jsonObject = new JSONObject(jsonInput);
            StringBuilder evenPairs = new StringBuilder();
            StringBuilder oddPairs = new StringBuilder();
            int index = 0;

            for (String key : jsonObject.keySet().stream().sorted().toList()) {
                String pair = key + ": " + jsonObject.get(key);
                if (index % 2 == 0) {
                    evenPairs.append(pair).append(", ");
                } else {
                    oddPairs.append(pair).append(", ");
                }
                index++;
            }

            // Loại bỏ dấu phẩy cuối cùng và ghép chuỗi
            if (!evenPairs.isEmpty()) evenPairs.setLength(evenPairs.length() - 2);
            if (!oddPairs.isEmpty()) oddPairs.setLength(oddPairs.length() - 2);
            String result = evenPairs + "; " + oddPairs;

            System.out.println("Processed Result: " + result);

            // Gửi chuỗi kết quả trở lại server
            service.submitCharacter(studentCode, qCode, result);
            System.out.println("Result submitted to server successfully.");

        } catch (Exception e) {
            System.err.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}