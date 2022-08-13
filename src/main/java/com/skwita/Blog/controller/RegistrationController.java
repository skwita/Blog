package com.skwita.Blog.controller;

import com.skwita.Blog.entity.User;
import com.skwita.Blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping
    public String processRegistration(@ModelAttribute("user") @Valid User user,
                                      Errors errors,
                                      Model model,
                                      HttpServletRequest request) {
        if (errors.hasErrors()) {
            return "registration";
        }
        if (!Objects.equals(user.getPassword(), user.getPasswordConfirm())) {
            model.addAttribute("passwordNotMatchError", true);
            return "registration";
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            model.addAttribute("usernameTaken", true);
            return "registration";
        }
        userService.registerDefaultUser(user);
        try {
            request.login(user.getUsername(), user.getPasswordConfirm());
            return "redirect:/";
        } catch (ServletException exception) {
            return "login";
        }
    }

}
