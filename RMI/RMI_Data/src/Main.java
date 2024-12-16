import RMI.DataService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String studentCode = "B21DCCN089";
        String qCode = "ncUCdOk4";

        try {
            // Kết nối tới RMI Registry Server
            Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099); // Thay "localhost" bằng địa chỉ server nếu cần
            DataService service = (DataService) registry.lookup("RMIDataService");

            // Triệu gọi phương thức requestData để nhận dữ liệu
            String receivedData = (String) service.requestData(studentCode, qCode);

            // Phân tích chuỗi để lấy N và K
            String[] parts = receivedData.split(";");
            if (parts.length == 2) {
                int N = Integer.parseInt(parts[0].trim());
                int K = Integer.parseInt(parts[1].trim());
                System.out.println("Received range: N = " + N + ", K = " + K);

                // Tìm các số đối xứng (Palindrome Numbers) trong khoảng [N, K)
                List<Integer> palindromeNumbers = findPalindromes(N, K);

                // Hiển thị kết quả
                System.out.println("Palindrome numbers: " + palindromeNumbers);

                // Gửi danh sách kết quả trở lại server
                service.submitData(studentCode, qCode, palindromeNumbers);
                System.out.println("Data submitted successfully.");
            } else {
                System.err.println("Invalid data format received: " + receivedData);
            }

        } catch (Exception e) {
            System.err.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Hàm tìm các số đối xứng (Palindrome Numbers) trong khoảng [N, K).
     */
    private static List<Integer> findPalindromes(int N, int K) {
        List<Integer> palindromes = new ArrayList<>();
        for (int i = N; i < K; i++) {
            if (isPalindrome(i)) {
                palindromes.add(i);
            }
        }
        return palindromes;
    }

    /**
     * Hàm kiểm tra một số có phải là số đối xứng (Palindrome Number) hay không.
     */
    private static boolean isPalindrome(int number) {
        String str = Integer.toString(number);
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left++) != str.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}