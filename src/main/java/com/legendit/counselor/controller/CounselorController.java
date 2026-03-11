package com.legendit.counselor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.legendit.counselor.entity.Counselor;
import com.legendit.counselor.service.CounselorService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CounselorController {

    @Autowired
    private CounselorService service;

    @GetMapping("/")
    public String index() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("counselor", new Counselor());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Counselor counselor, Model model) {

        service.registerCounselor(counselor);

        model.addAttribute("msg", "Registration Successful! Please Login");
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        Counselor c = service.login(email, password);

        if (c != null) {

            session.setAttribute("counselor", c);

            return "dashboard";
        }

        model.addAttribute("msg", "Invalid Credentials");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();
        return "redirect:/";
    }
}