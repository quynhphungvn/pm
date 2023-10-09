package quynh.java.webapp.pm.dao;

import java.util.List;

import quynh.java.webapp.pm.model.Domain;
import quynh.java.webapp.pm.model.Project;

public interface DomainDao {
    List<Domain> getAll(Project Project);
    Domain getById(int id);
    Domain get1(String name, Project project);
    Domain add(Domain domain);
    Domain update(Domain domain);
    Domain delete(Domain domain);
}
