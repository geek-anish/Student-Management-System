package net.javaguides.sms.controller;

import jakarta.validation.Valid;
import net.javaguides.sms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import net.javaguides.sms.entity.Student;
import net.javaguides.sms.service.StudentService;

@Controller
public class StudentController {


    private final StudentService studentService;

    public StudentController(StudentService studentService) {

        this.studentService = studentService;
    }

    // handler method to handle list students and return model and view
    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        // System.out.println(studentService.getAllStudents().get(4));
        return "students";
    }
    @GetMapping("/students/new")
    public String createStudentForm(Model model) {

        // create student object to hold student form data
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student";

    }
    @PostMapping("/students")
    public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, Model model, @RequestParam("email") String email) {

        boolean userExists = studentService.doesUserExist(email);

        if (userExists) {
            model.addAttribute("userExistenceMessage", "User with this email is already exists.");
            return "create_student";
        }

        if (bindingResult.hasErrors()) {
            return "create_student";
        } else {
            studentService.saveStudent(student);

            return "redirect:/students";
        }
    }

    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "edit_student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute("student") Student student,
                                Model model) {


        // save updated student object
        studentService.updateStudent(student, id);
        return "redirect:/students";
    }

    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }

}