package com.sfsb.studentsSFSB.students.controller;

import com.sfsb.studentsSFSB.students.model.Students;
import com.sfsb.studentsSFSB.students.service.StudentsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StudentController {
    private StudentsServiceImpl service;

    public StudentController(StudentsServiceImpl service) {
        this.service = service;
    }

    @GetMapping("")
    public String showHomePage(){
        return "index";
    }

    @GetMapping("/students/new")
    public String showForm(){
        return "form";

    }

    @GetMapping("/students")
    public String showStudentsList(Model model){
        List<Students> listStudents =  service.findAll();
        model.addAttribute("listStudents", listStudents);

        return "students";
    }




}
