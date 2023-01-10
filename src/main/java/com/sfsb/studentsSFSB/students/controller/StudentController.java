package com.sfsb.studentsSFSB.students.controller;

import com.sfsb.studentsSFSB.students.model.Students;
import com.sfsb.studentsSFSB.students.service.StudentsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

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
    public String showForm(Model model){
        model.addAttribute("newStudent", new Students());
        return "student_form";

    }

    @GetMapping("/students")
    public String showStudentsList(Model model){
        List<Students> listStudents =  service.findAll();
        model.addAttribute("listStudents", listStudents);

        return "students";
    }

    @PostMapping("/students/save")
    public String saveStudent(Students newStudent){
        //calculate the work hours per record
        newStudent.setWorkHoursPerDay(newStudent.calculateWorkHoursPerDay());
        service.save(newStudent);
        return "redirect:/students";

    }




}
