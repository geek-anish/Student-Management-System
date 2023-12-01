package net.javaguides.sms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import net.javaguides.sms.entity.Student;
import net.javaguides.sms.repository.StudentRepository;
import net.javaguides.sms.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        super();
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    @Override
    public Student saveStudent(Student student) {

        if (doesUserExist(student.getEmail())) {
            System.out.println("User already exists");
            return null;
        } else {
            return studentRepository.save(student);
        }
    }

    public boolean doesUserExist(String email) {

        Student existingUser = studentRepository.findByEmail(email);
        return (existingUser != null);
    }


    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student updateStudent(Student student, Long id) {

        // get student from database by id

        Student existingStudent = studentRepository.findById(id).get();
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());

        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }


}