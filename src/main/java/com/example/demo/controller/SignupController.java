package com.example.demo.controller;

import com.example.demo.form.SignupForm;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SignupController {

    private final UserService userService;

    @Autowired
    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("signupForm", new SignupForm());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignupForm form, RedirectAttributes redirectAttributes) {
        String message = userService.addUser(form);
        if (message.equals("User added successfully")) {
            return "redirect:/signin";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "記入に誤りがあります");
            return "redirect:/signup";
        }
    }
}
