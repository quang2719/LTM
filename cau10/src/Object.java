/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import RMI.ObjectService;
import RMI.Book;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.registry.*;
import java.rmi.RemoteException;

/**
 *
 * @author Admin
 */
public class Object {
// Phương thức tạo mã code cho sách
    public static String generateBookCode(String author, int yearPublished, String title, int pageCount) {
        // Lấy chữ cái đầu của họ và tên đầu tiên
        String[] authorParts = author.split(" ");  // Tách tên tác giả thành các phần (họ, tên)
        String authorInitials = authorParts[0].substring(0, 1) + authorParts[authorParts.length - 1].substring(0, 1);
        
        // Lấy hai chữ số cuối cùng của năm xuất bản
        String yearSuffix = String.valueOf(yearPublished).substring(2);
        
        // Lấy tổng số chữ cái trong title của sách
        int titleLength = title.replace(" ", "").length();  // Loại bỏ khoảng trắng và tính tổng số ký tự
        
        // Lấy số chữ số của pageCount và đảm bảo có 3 chữ số
        String pageCountStr = String.format("%03d", pageCount); // Đảm bảo có đủ 3 chữ số
        
        // Kết hợp tất cả thành phần để tạo mã code
        return authorInitials + yearSuffix + titleLength + pageCountStr;
    }    
    public static void main(String[] args) throws NotBoundException {
        try {
            // Bước 1: Kết nối tới RMI Registry
            Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);  // Đảm bảo địa chỉ và port của RMI Registry chính xác
            ObjectService objectService = (ObjectService) registry.lookup("RMIObjectService");

            // Bước 2: Triệu gọi phương thức requestObject để nhận đối tượng Book
            String studentCode = "B21DCCN632"; // Mã sinh viên
            String qCode = "ys1kmL1l"; // Mã câu hỏi
            Book receivedBook = (Book) objectService.requestObject(studentCode, qCode); // Nhận đối tượng Book từ server

            // In ra thông tin đối tượng Book nhận được (tùy chọn)
            System.out.println("Received Book: " + receivedBook);

            // Bước 3: Tạo mã code cho sách
            String bookCode = generateBookCode(receivedBook.getAuthor(), receivedBook.getYearPublished(), receivedBook.getTitle(), receivedBook.getPageCount());
            System.out.println("Generated Book Code: " + bookCode);

            // Bước 4: Cập nhật giá trị code trong đối tượng Book
            receivedBook.setCode(bookCode);

            // Bước 5: Triệu gọi phương thức submitObject để gửi đối tượng Book đã xử lý trở lại server
            objectService.submitObject(studentCode, qCode, (Serializable) receivedBook);
            System.out.println("Book object submitted successfully.");

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}