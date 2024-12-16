/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cau15;

/**
 *
 * @author Admin
 */
import com.example.jnpws.ObjectService;
import com.example.jnpws.ObjectService_Service;
import com.example.jnpws.Student;

import java.util.*;
import java.util.stream.Collectors;

public class client {
    public static void main(String[] args) {
        // Bước a: Kết nối đến ObjectService
        ObjectService_Service service = new ObjectService_Service();
        ObjectService port = service.getObjectServicePort();

        String studentCode = "B15DCCN999";
        String qCode = "M7H6fSPk";

        // Triệu gọi phương thức requestObject
        List<Student> students = port.requestObject(studentCode, qCode);

        // Bước b: Phân nhóm sinh viên theo điểm
        Map<String, List<Student>> groupedStudents = students.stream()
                .collect(Collectors.groupingBy(student -> {
                    float score = student.getScore();
                    if (score >= 8.0) return "A";
                    else if (score >= 6.5) return "B";
                    else if (score >= 5.0) return "C";
                    else return "D";
                }));

        // Chuyển đổi danh sách thành định dạng List<String[]>
        List<String[]> data = new ArrayList<>();
        groupedStudents.forEach((group, studentList) -> {
            for (Student student : studentList) {
                data.add(new String[]{group, student.getName(), String.valueOf(student.getScore())});
            }
        });

        // Bước c: Triệu gọi phương thức submitObject
        port.submitObject(studentCode, qCode, data);

        // Bước d: Kết thúc chương trình
    }
}
