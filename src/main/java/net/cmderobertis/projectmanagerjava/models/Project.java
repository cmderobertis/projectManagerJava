package net.cmderobertis.projectmanagerjava.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Title is required")
    @Size(min = 3, max = 30, message = "Title must be between 3 and 30 characters")
    private String title;
    @NotEmpty(message = "Description is required")
    @Size(min = 3, max = 140, message = "Description must be between 3 and 140 characters")
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leader_id")
    private User leader;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "assignees_projects",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "assignee_id")
    )
    private List<User> assignees;
    public Project() {}
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public User getLeader() {return leader;}
    public void setLeader(User leader) {this.leader = leader;}
    public List<User> getAssignees() {return assignees;}
    public void setAssignees(List<User> assignees) {this.assignees = assignees;}
}
