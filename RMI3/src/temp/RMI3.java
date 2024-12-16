package temp;




import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import RMI.ByteService;
/**
 *
 * @author Admin
 */
public class RMI3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String studentCode = "B21DCCN089";
        String qCode = "onxGlivf";

        try {
            // Kết nối tới RMI Registry Server
            Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099); // Thay "localhost" bằng địa chỉ server nếu cần
            ByteService service = (ByteService) registry.lookup("RMIByteService");

            // Triệu gọi phương thức requestData để nhận mảng dữ liệu nhị phân từ server
            byte[] data = service.requestData(studentCode, qCode);
            System.out.println("Received binary data: ");
            for (byte b : data) {
                System.out.print(b + " ");
            }
            System.out.println();

            // Phân chia dữ liệu nhị phân thành hai phần: chẵn và lẻ
            ArrayList<Byte> evenBytes = new ArrayList<>();
            ArrayList<Byte> oddBytes = new ArrayList<>();

            for (byte b : data) {
                if (b % 2 == 0) {
                    evenBytes.add(b);
                } else {
                    oddBytes.add(b);
                }
            }

            // Ghép hai phần lại thành một mảng mới
            byte[] processedData = new byte[evenBytes.size() + oddBytes.size()];
            int index = 0;
            for (byte b : evenBytes) {
                processedData[index++] = b;
            }
            for (byte b : oddBytes) {
                processedData[index++] = b;
            }

            // Hiển thị mảng sau khi xử lý
            System.out.println("Processed binary data (even followed by odd): ");
            for (byte b : processedData) {
                System.out.print(b + " ");
            }
            System.out.println();

            // Gửi dữ liệu đã xử lý trở lại server
            service.submitData(studentCode, qCode, processedData);
            System.out.println("Processed data submitted successfully.");

        } catch (NotBoundException | RemoteException e) {
            System.err.println("Client exception: " + e.getMessage());
        }
    }
    
}
