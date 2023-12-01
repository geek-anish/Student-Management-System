package net.javaguides.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final StudentController studentController;


    public MainController(StudentController studentController) {
        this.studentController = studentController;
    }


    @GetMapping("/login")
    public String login()
    {
        return "login";
    }

    @GetMapping("/")
    public String home(Model model)
    {
       return studentController.listStudents(model);
//        return "students";
    }
}