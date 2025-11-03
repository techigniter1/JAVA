package com.example.service;

import com.example.dao.StudentDAO;
import com.example.model.Payment;
import com.example.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class FeeService {

    @Autowired
    private StudentDAO studentDAO;

    @Transactional
    public void payFee(int studentId, double amount) {
        Student student = studentDAO.findById(studentId);
        if (student == null) {
            throw new RuntimeException("Student not found");
        }
        if (student.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        student.setBalance(student.getBalance() - amount);
        studentDAO.update(student);

        // Record payment
        Payment payment = new Payment(student, amount, new Date(), "PAYMENT");
        // Assuming we have a PaymentDAO, but for simplicity, we'll skip saving payment here
        // In a real app, inject PaymentDAO and save
    }

    @Transactional
    public void refundFee(int studentId, double amount) {
        Student student = studentDAO.findById(studentId);
        if (student == null) {
            throw new RuntimeException("Student not found");
        }
        student.setBalance(student.getBalance() + amount);
        studentDAO.update(student);

        // Record refund
        Payment payment = new Payment(student, amount, new Date(), "REFUND");
        // Skip saving for simplicity
    }
}
