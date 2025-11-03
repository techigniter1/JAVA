package com.example;

import com.example.config.AppConfig;
import com.example.dao.StudentDAO;
import com.example.model.Course;
import com.example.model.Student;
import com.example.service.FeeService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        StudentDAO studentDAO = context.getBean(StudentDAO.class);
        FeeService feeService = context.getBean(FeeService.class);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Online Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. View Student by ID");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Pay Fee");
            System.out.println("7. Refund Fee");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter course name: ");
                    String courseName = scanner.nextLine();
                    System.out.print("Enter course duration: ");
                    int duration = scanner.nextInt();
                    System.out.print("Enter initial balance: ");
                    double balance = scanner.nextDouble();

                    Course course = new Course(courseName, duration);
                    Student student = new Student(name, course, balance);
                    studentDAO.save(student);
                    System.out.println("Student added successfully.");
                    break;
                case 2:
                    System.out.println("All Students:");
                    for (Student s : studentDAO.findAll()) {
                        System.out.println(s);
                    }
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    int id = scanner.nextInt();
                    Student s = studentDAO.findById(id);
                    if (s != null) {
                        System.out.println(s);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter student ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    Student updateStudent = studentDAO.findById(updateId);
                    if (updateStudent != null) {
                        System.out.print("Enter new name: ");
                        updateStudent.setName(scanner.nextLine());
                        System.out.print("Enter new balance: ");
                        updateStudent.setBalance(scanner.nextDouble());
                        studentDAO.update(updateStudent);
                        System.out.println("Student updated successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter student ID to delete: ");
                    int deleteId = scanner.nextInt();
                    studentDAO.delete(deleteId);
                    System.out.println("Student deleted successfully.");
                    break;
                case 6:
                    System.out.print("Enter student ID: ");
                    int payId = scanner.nextInt();
                    System.out.print("Enter amount to pay: ");
                    double payAmount = scanner.nextDouble();
                    try {
                        feeService.payFee(payId, payAmount);
                        System.out.println("Fee paid successfully.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 7:
                    System.out.print("Enter student ID: ");
                    int refundId = scanner.nextInt();
                    System.out.print("Enter amount to refund: ");
                    double refundAmount = scanner.nextDouble();
                    try {
                        feeService.refundFee(refundId, refundAmount);
                        System.out.println("Fee refunded successfully.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 8:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        context.close();
        scanner.close();
    }
}
