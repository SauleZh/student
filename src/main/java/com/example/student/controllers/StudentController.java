
package com.example.student.controllers;

import com.example.student.models.Student;
import com.example.student.reposotories.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/student")
public class StudentController { @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/main")
    public String allStudent(Model model){
        List<Student> students = (List<Student>) studentRepository.findAll();
        model.addAttribute("student",students);
        return "student";
    }
    @GetMapping("/add")
    public String studentForm(Model model){
        model.addAttribute("student",new Student());
        return "student_add_form";
    }
    @PostMapping("/add")
    public String add(@ModelAttribute Student student){
        studentRepository.save(student);
        return "redirect:/student/main";
    }
    @RequestMapping(value = "/deleteStudent",method = RequestMethod.GET)
    public ModelAndView deleteContact(@RequestParam("id") long idd){
        studentRepository.deleteById(idd);
        return new ModelAndView("redirect:/student/main");
    }

}

