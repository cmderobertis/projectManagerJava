package net.cmderobertis.projectmanagerjava.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "First Name is required")
    @Size(min = 3, max = 30, message = "First Name must be between 3 and 30 characters")
    private String firstName;
    @NotEmpty(message = "Last Name is required")
    @Size(min = 3, max = 30, message = "Last Name must be between 3 and 30 characters")
    private String lastName;
    @NotEmpty(message="Email is required")
    @Email(message="Please enter a valid email")
    private String email;
    @NotEmpty(message="Password is required")
    @Size(min=8, max=128, message="Password must be between 8 and 128 characters")
    @Pattern(regexp = "^(?=.*?[A-Z]).{8,}$", message = "At least one uppercase letter required")
    @Pattern(regexp = "^(?=.*?[a-z]).{8,}$", message = "At least one lowercase letter required")
    @Pattern(regexp = "^(?=.*?[0-9]).{8,}$", message = "At least one number required")
    private String password;
    @Transient
    @NotEmpty(message="Confirm Password is required")
    @Size(min=8, max=128, message="Confirm Password must be between 8 and 128 characters")
    private String confirm;
    @OneToMany(mappedBy = "leader", fetch = FetchType.LAZY)
    private List<Project> ledProjects;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "assignees_projects",
            joinColumns = @JoinColumn(name = "assignee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> assignedProjects;
    public User() {}
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public String getConfirm() {return confirm;}
    public void setConfirm(String confirm) {this.confirm = confirm;}
    public List<Project> getLedProjects() {return ledProjects;}
    public void setLedProjects(List<Project> ledProjects) {this.ledProjects = ledProjects;}
    public List<Project> getAssignedProjects() {return assignedProjects;}
    public void setAssignedProjects(List<Project> assignedProjects) {this.assignedProjects = assignedProjects;}
}
