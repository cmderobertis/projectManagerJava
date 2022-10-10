package net.cmderobertis.projectmanagerjava.controllers;

import net.cmderobertis.projectmanagerjava.models.Project;
import net.cmderobertis.projectmanagerjava.models.User;
import net.cmderobertis.projectmanagerjava.services.ProjectService;
import net.cmderobertis.projectmanagerjava.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@Controller
public class ProjectController {
    @Autowired
    ProjectService service;
    @Autowired
    UserService userService;

    @GetMapping("/projects/new")
    String createForm(@ModelAttribute("project") Project project, Model model, HttpSession session) {
        model.addAttribute("user", userService.getOne((Long) session.getAttribute("userId")));
        return "createProject.jsp";
    }
    @PostMapping("/projects")
    String create(@ModelAttribute("project") Project project) {
        service.create(project);
        return "redirect:/projects";
    }
    @GetMapping("/projects")
    String showAll(Model model, HttpSession session) {
        User user = userService.getOne((Long) session.getAttribute("userId"));
        model.addAttribute("assignedProjects", service.getAssignedProjects(user));
        model.addAttribute("unassignedProjects", service.getUnassignedProjects(user));
        model.addAttribute("user", user);
        return "projects.jsp";
    }


    @GetMapping("/projects/{id}")
    String showOne(@PathVariable Long id, Model model, HttpSession session) {
        Project project = service.getOne(id);
        model.addAttribute("project", project);
        model.addAttribute("user", userService.getOne((Long) session.getAttribute("userId")));
        return "showProject.jsp";
    }
    @GetMapping("/projects/{id}/edit")
    String updateForm(@PathVariable("id") Long id, Model model, HttpSession session) {
        Project project = service.getOne(id);
        if (!Objects.equals((Long)session.getAttribute("userId"), project.getLeader().getId())) {
            return "redirect:/projects";
        }
        User user = userService.getOne((Long) session.getAttribute("userId"));
        model.addAttribute("project", project);
        model.addAttribute("user", user);
        return "updateProject.jsp";
    }
    @PutMapping("/projects/{id}")
    String update(
            @Valid
            @ModelAttribute("project") Project project,
            BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("error updating project");
            return "updateProject.jsp";
        } else {
            service.update(project);
            return "redirect:/projects";
        }
    }
    @DeleteMapping("/projects/{id}")
    String delete(@PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/projects";
    }
    @PutMapping("/projects/{id}/assign")
    String assignProject(@PathVariable("id") Long id, HttpSession session) {
        User assignee = userService.getOne((Long) session.getAttribute("userId"));
        Project project = service.getOne(id);
        service.assign(project, assignee);
        return "redirect:/projects";
    }
    @PutMapping("/projects/{id}/unassign")
    String unassignProject(@PathVariable("id") Long id, HttpSession session) {
        User assignee = userService.getOne((Long) session.getAttribute("userId"));
        Project project = service.getOne(id);
        service.unassign(project, assignee);
        return "redirect:/projects";
    }
}
