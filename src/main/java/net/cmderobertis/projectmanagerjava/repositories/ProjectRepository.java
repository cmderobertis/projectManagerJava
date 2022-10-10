package net.cmderobertis.projectmanagerjava.repositories;

import net.cmderobertis.projectmanagerjava.models.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    List<Project> findAll();
}
