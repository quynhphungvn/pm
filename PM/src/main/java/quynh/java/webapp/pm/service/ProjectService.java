package quynh.java.webapp.pm.service;

import java.util.List;

import quynh.java.webapp.pm.model.Project;

public interface ProjectService {
    List<Project> getAll();

    Project addProject(String projectName);

	Project getById(int projectId);

	Project updateProject(int projectId, String newName, String overview, String scope, String technology);

	Project deleteProject(int projectId);
}
