package quynh.java.webapp.pm.dao;

import java.util.List;

import quynh.java.webapp.pm.model.Project;

public interface ProjectDao {
    List<Project> getAll();
    Project getById(int id);
    Project getByName(String name);
    Project add(Project project);
    Project update(Project project);
    Project delete(Project project);
}
