package quynh.java.webapp.pm.service.impl;

import java.util.List;

import quynh.java.webapp.pm.dao.ProjectDao;
import quynh.java.webapp.pm.dao.hql_impl.ProjectDaoImpl;
import quynh.java.webapp.pm.exception.EntityExistedException;
import quynh.java.webapp.pm.model.Project;
import quynh.java.webapp.pm.service.ProjectService;

public class ProjectServiceImpl implements ProjectService {
    ProjectDao projectDao = new ProjectDaoImpl();
    @Override
    public List<Project> getAll() {
       return projectDao.getAll();
    }
    @Override
    public Project addProject(String projectName) {
        Project p = new Project();
        p.setName(projectName);
        return projectDao.add(p);     
    }
	@Override
	public Project getById(int projectId) {
		return projectDao.getById(projectId);
	}
	@Override
	public Project updateProject(int projectId, String newName, String overview, String scope, String technology) {
		Project project = projectDao.getById(projectId);
		if (project != null) {
			if (newName != null)
				project.setName(newName);
			project.setOverview(overview);
			project.setScope(scope);
			project.setTechnology(technology);
			projectDao.update(project);
		}
		return project;
	}
	@Override
	public Project deleteProject(int projectId) {
		Project project = projectDao.getById(projectId);
		if (project != null)
			projectDao.delete(project);
		return project;
	}
    
}
