package com.sfsb.studentsSFSB.students.controller;

import com.lowagie.text.DocumentException;
import com.sfsb.studentsSFSB.students.model.GenerateReportPDF;
import com.sfsb.studentsSFSB.students.model.Students;
import com.sfsb.studentsSFSB.students.service.StudentsServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
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
   @GetMapping("/students/delete/{id}")
   public String deleteStudent(@PathVariable("id") Integer id){
        service.deleteById(id);
       return "redirect:/students";
   }
   @GetMapping("/students/report")
   public void generateReportPDF(HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=report.pdf";

        response.setHeader(headerKey, headerValue);

        List<Students> listStudents = service.findAll();
       GenerateReportPDF report = new GenerateReportPDF(listStudents);
       report.export(response);

   }




}
