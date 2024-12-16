
import RMI.DataService;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.rmi.registry.*;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class main {
    public static List<Integer> primeFactors(int n) {
        List<Integer> factors = new ArrayList<>();
        
        // Kiểm tra số 2
        while (n % 2 == 0) {
            factors.add(2);
            n /= 2;
        }

        // Kiểm tra các số lẻ từ 3 trở đi
        for (int i = 3; i * i <= n; i += 2) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }

        // Nếu n còn lại là một số nguyên tố lớn hơn 2
        if (n > 2) {
            factors.add(n);
        }

        return factors;
    }

    public static void main(String[] args) {
        try {
            // Kết nối tới RMI Registry
            Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099); // Địa chỉ và port của Registry Server
            DataService dataService = (DataService) registry.lookup("RMIDataService");

            // Triệu gọi phương thức requestData để nhận số nguyên N
            String studentCode = "B21DCCN632"; // Mã sinh viên
            String qCode = "0DJptnqK"; // Mã câu hỏi
            Integer N = (Integer) dataService.requestData(studentCode, qCode); // Nhận số nguyên N từ server

            // Kiểm tra số N nhận được
            System.out.println("Received N: " + N);

            // Phân rã số N thành các thừa số nguyên tố
            List<Integer> primeFactorsList = primeFactors(N);
            System.out.println("Prime factors of " + N + ": " + primeFactorsList);

            // Gửi danh sách các thừa số nguyên tố trở lại server
            dataService.submitData(studentCode, qCode, primeFactorsList);

            System.out.println("Prime factors submitted successfully.");

        } catch (NotBoundException | RemoteException e) {
            e.printStackTrace();
        }
    }
}