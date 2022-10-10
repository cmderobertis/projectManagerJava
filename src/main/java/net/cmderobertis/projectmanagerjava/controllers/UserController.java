package net.cmderobertis.projectmanagerjava.controllers;

import net.cmderobertis.projectmanagerjava.models.LoginUser;
import net.cmderobertis.projectmanagerjava.models.User;
import net.cmderobertis.projectmanagerjava.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        if (session.getAttribute("userId") != null) {
            return "redirect:/projects";
        }
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser,
                           BindingResult result, Model model, HttpSession session) {
        User registeredUser = service.register(newUser, result);
        if(result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        session.setAttribute("userId", registeredUser.getId());
        return "redirect:/projects";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin,
                        BindingResult result, Model model, HttpSession session) {
        User user = service.login(newLogin, result);
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
        session.setAttribute("userId", user.getId());
        return "redirect:/projects";
    }

    @GetMapping("/logout")
    String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}