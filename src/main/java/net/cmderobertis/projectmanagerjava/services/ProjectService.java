package net.cmderobertis.projectmanagerjava.services;

import net.cmderobertis.projectmanagerjava.models.Project;
import net.cmderobertis.projectmanagerjava.models.User;
import net.cmderobertis.projectmanagerjava.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private final ProjectRepository repo;
    public ProjectService(ProjectRepository repo) {
        this.repo = repo;
    }
    // CREATE
    public void create(Project project) {
        repo.save(project);
    }
    // READ
    public List<Project> getAll() {
        return repo.findAll();
    }
    public List<Project> getAssignedProjects(User user) {
        return getAll().stream().filter((project) -> project.getAssignees().contains(user)).collect(Collectors.toList());
    }
    public List<Project> getUnassignedProjects(User user) {
        return getAll().stream().filter((project) -> !project.getAssignees().contains(user)).collect(Collectors.toList());
    }
    public Project getOne(Long id) {
        Optional<Project> project = repo.findById(id);
        return project.orElse(null);
    }
    // UPDATE
    public void update(Project project) {
        repo.save(project);
    }
    //DELETE
    public void delete(Long id) {
        repo.deleteById(id);
    }
    public void assign(Project project, User assignee) {
        project.getAssignees().add(assignee);
        repo.save(project);
    }
    public void unassign(Project project, User assignee) {
        project.getAssignees().remove(assignee);
        repo.save(project);
    }
}
