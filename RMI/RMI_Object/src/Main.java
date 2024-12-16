import RMI.ObjectService;
import RMI.Product;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args) {
        String studentCode = "B21DCCN089";
        String qAlias = "8UbgfQPK";

        try {
            // Kết nối tới RMI Registry Server
            Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099); // Thay "localhost" bằng địa chỉ server nếu cần
            ObjectService service = (ObjectService) registry.lookup("RMIObjectService");

            // Triệu gọi phương thức requestObject để lấy đối tượng sản phẩm
            Product product = (Product) service.requestObject(studentCode, qAlias);
            System.out.println("Received Product:");
            System.out.println(product);

            // Thực hiện chuẩn hóa đối tượng
            normalizeProduct(product);
            System.out.println("Normalized Product:");
            System.out.println(product);

            // Gửi đối tượng đã chuẩn hóa trở lại server
            service.submitObject(studentCode, qAlias, product);
            System.out.println("Product submitted successfully.");

        } catch (Exception e) {
            System.err.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Hàm chuẩn hóa đối tượng sản phẩm:
     * - Chuyển mã sản phẩm (code) thành chữ in hoa.
     * - Cập nhật giá xuất (exportPrice) = giá nhập (importPrice) + 20%.
     */
    private static void normalizeProduct(Product product) {
        if (product != null) {
            // Chuyển mã sản phẩm thành chữ in hoa
            product.setCode(product.getCode().toUpperCase());

            // Cập nhật giá xuất
            double newExportPrice = product.getImportPrice() * 1.2;
            product.setExportPrice(newExportPrice);
        }
    }
}