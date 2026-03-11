package com.legendit.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.legendit.counselor.entity.Counselor;
import com.legendit.student.entity.Student;
import com.legendit.student.service.StudentService;

import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model){

        Counselor c = (Counselor) session.getAttribute("counselor");

        Long id = c.getId();

        model.addAttribute("open", service.getOpen(id));
        model.addAttribute("enrolled", service.getEnrolled(id));
        model.addAttribute("lost", service.getLost(id));

        return "dashboard";
    }

    @GetMapping("/add-enquiry")
    public String addPage(Model model){

        model.addAttribute("student", new Student());
        return "add_enquiry";
    }

    @PostMapping("/save-enquiry")
    public String save(Student student, HttpSession session){

        Counselor c = (Counselor) session.getAttribute("counselor");

        student.setCounselor(c);

        service.save(student);

        return "redirect:/view-enquiries";
    }

    @GetMapping("/view-enquiries")
    public String view(HttpSession session, Model model){

        Counselor c = (Counselor) session.getAttribute("counselor");

        model.addAttribute("list", service.getAll(c.getId()));

        return "view_enquiries";
    }

    @GetMapping("/search")
    public String search(@RequestParam String phone, Model model){

        model.addAttribute("list", service.searchPhone(phone));

        return "view_enquiries";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Long id, Model model){

        model.addAttribute("student", service.getById(id));

        return "edit_enquiry";
    }

    @PostMapping("/update")
    public String update(Student student){

        service.save(student);

        return "redirect:/view-enquiries";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id){

        service.delete(id);

        return "redirect:/view-enquiries";
    }
}