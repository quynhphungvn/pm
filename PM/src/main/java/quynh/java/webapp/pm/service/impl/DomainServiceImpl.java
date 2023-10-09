package quynh.java.webapp.pm.service.impl;

import java.util.List;

import quynh.java.webapp.pm.dao.DomainDao;
import quynh.java.webapp.pm.dao.ProjectDao;
import quynh.java.webapp.pm.dao.hql_impl.DomainDaoImpl;
import quynh.java.webapp.pm.dao.hql_impl.ProjectDaoImpl;
import quynh.java.webapp.pm.model.Domain;
import quynh.java.webapp.pm.model.Project;
import quynh.java.webapp.pm.service.DomainService;
import quynh.java.webapp.pm.util.plantuml.DiagramType;
import quynh.java.webapp.pm.util.plantuml.DiagramUtil;

public class DomainServiceImpl implements DomainService {
    private ProjectDao projectDao = new ProjectDaoImpl();
    private DomainDao domainDao = new DomainDaoImpl();
    
    @Override
    public List<Domain> getAll(int projectId) {
        Project project = projectDao.getById(projectId);
        return domainDao.getAll(project);
    }

	@Override
	public Domain addDomain(String domainName, int projectId) {
		Project project = projectDao.getById(projectId);
		Domain domain = null;
		if (project != null)
		{
			domain = new Domain();
			domain.setName(domainName);
			domain.setProject(project);
			domain.setErdDiagram(DiagramUtil.getInitText(DiagramType.ERD.toString()));
			domain.setClassDiagram(DiagramUtil.getInitText(DiagramType.CLASS.toString()));
			domainDao.add(domain);
		}
		return domain;
	}

	@Override
	public Domain getById(int domainId) {
		return domainDao.getById(domainId);
	}

	@Override
	public Domain updateDomain(int domainId, String newName, String overview, String scope, String technology, String note) {
		Domain domain = domainDao.getById(domainId);
		if (domain != null) {
			if (newName.isBlank() == false)
				domain.setName(newName);
			domain.setOverview(overview);
			domain.setScope(scope);
			domain.setTechnology(technology);
			domain.setNote(note);
			domainDao.update(domain);
		}
		return domain;
	}

	@Override
	public Domain deleteDomain(int domainId) {
		Domain domain = domainDao.getById(domainId);
		if (domain != null)
			domainDao.delete(domain);
		return domain;
	}

	@Override
	public Domain updateRequirement(Domain domain, String requirement) {
		domain.setRequirement(requirement);
		return domainDao.update(domain);
	}

	@Override
	public Domain updateErd(int domainId, String erdDiagram) {
		Domain domain = null;
		if (domainId != 0) {
			domain = domainDao.getById(domainId);
			domain.setErdDiagram(erdDiagram);
			domainDao.update(domain);
		}
		return domain;
	}
	
	@Override
	public Domain updateClassDiagram(int domainId, String classDiagram) {
		Domain domain = null;
		if (domainId != 0) {
			domain = domainDao.getById(domainId);
			domain.setClassDiagram(classDiagram);;
			domainDao.update(domain);
		}
		return domain;
	}

}
