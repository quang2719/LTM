
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import vn.medianews.DataService;
import vn.medianews.DataService_Service;

public class Main {
    public static void main(String[] args) {
        String studentCode = "B21DCCN089";
        String qCode = "h2sIpgh8";

        try {
            URL wsdlURL = new URL("http://203.162.10.109:8080/JNPWS/DataService?wsdl");
            DataService_Service service = new DataService_Service(wsdlURL);
            DataService port = service.getDataServicePort();


            // Triệu gọi phương thức getData để nhận danh sách số nguyên từ server
            List<Integer> dataList = port.getData(studentCode, qCode);
            System.out.println("Received Data List: " + dataList);

            // Tìm số lớn nhất có thể tạo ra
            String largestNumber = findLargestNumber(dataList);
            System.out.println("Largest Number: " + largestNumber);

            // Triệu gọi phương thức submitDataString để gửi kết quả
            port.submitDataString(studentCode, qCode, largestNumber);
            System.out.println("Data submitted successfully.");

        } catch (Exception e) {
            System.err.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Tìm số lớn nhất có thể tạo ra từ danh sách các số nguyên.
     * @param numbers Danh sách số nguyên
     * @return Số lớn nhất (dưới dạng chuỗi)
     */
    private static String findLargestNumber(List<Integer> numbers) {
        // Sắp xếp danh sách theo thứ tự giảm dần dựa trên phép nối chuỗi
        Collections.sort(numbers, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                String ab = a.toString() + b.toString();
                String ba = b.toString() + a.toString();
                return ba.compareTo(ab); // So sánh để đảm bảo thứ tự giảm dần
            }
        });

        // Kết hợp các số trong danh sách thành một chuỗi
        StringBuilder largestNumber = new StringBuilder();
        for (Integer number : numbers) {
            largestNumber.append(number);
        }
        return largestNumber.toString();
    }
}