package net.javaguides.sms.service;

import net.javaguides.sms.entity.Student;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    Student saveStudent(Student student);

    Student getStudentById(Long id);

    Student updateStudent(Student student, Long id);

    void deleteStudentById(Long id);



    boolean doesUserExist(String email);
}
