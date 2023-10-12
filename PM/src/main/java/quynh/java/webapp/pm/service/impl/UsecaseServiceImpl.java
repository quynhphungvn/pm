package quynh.java.webapp.pm.service.impl;

import java.time.LocalDate;
import java.util.List;

import quynh.java.webapp.pm.dao.DomainDao;
import quynh.java.webapp.pm.dao.UsecaseDao;
import quynh.java.webapp.pm.dao.hql_impl.DomainDaoImpl;
import quynh.java.webapp.pm.dao.hql_impl.UsecaseDaoImpl;
import quynh.java.webapp.pm.model.Domain;
import quynh.java.webapp.pm.model.Project;
import quynh.java.webapp.pm.model.Usecase;
import quynh.java.webapp.pm.service.UsecaseService;
import quynh.java.webapp.pm.util.plantuml.DiagramType;
import quynh.java.webapp.pm.util.plantuml.DiagramUtil;

public class UsecaseServiceImpl implements UsecaseService {
	UsecaseDao usecaseDao = new UsecaseDaoImpl();
	DomainDao domainDao = new DomainDaoImpl();
	
	@Override
	public List<Usecase> getAll(Domain selectedDomain) {
		return usecaseDao.getAll(selectedDomain);
	}
	@Override
	public Usecase getById(int usecaseId) {
		return usecaseDao.getById(usecaseId);
	}
	@Override
	public Usecase add(String name, int domainId) {
		Domain domain = domainDao.getById(domainId);
		Usecase usecase = null;
		if (domain != null)
		{
			usecase = new Usecase();
			usecase.setName(name);
			usecase.setCreatedDate(LocalDate.now());
			usecase.setActivityDiagram(DiagramUtil.getInitText(DiagramType.ACTIVITY.toString()));
			usecase.setSequenceDiagram(DiagramUtil.getInitText(DiagramType.SEQUENCE.toString()));
			usecase.setDomain(domain);
			usecaseDao.add(usecase);
		}
		return usecase;
	}
	@Override
	public Usecase delete(int usecaseId) {
		Usecase usecase = usecaseDao.getById(usecaseId);
		if (usecase != null)
			usecaseDao.delete(usecase);
		return usecase;
	}
	@Override
	public Usecase updateSpec(Usecase usecaseData) {
		Usecase usecase = usecaseDao.getById(usecaseData.getId());
		if (usecase != null) {
			usecase.setCreatedBy(usecaseData.getCreatedBy());
			usecase.setActors(usecaseData.getActors());
			usecase.setTriggerContext(usecaseData.getTriggerContext());
			usecase.setDescription(usecaseData.getDescription());
			usecase.setPreconditions(usecaseData.getPreconditions());
			usecase.setPostconditions(usecaseData.getPostconditions());
			usecase.setNormalFlow(usecaseData.getNormalFlow());
			usecase.setAlternativeFlow(usecaseData.getAlternativeFlow());
			usecase.setExceptions(usecaseData.getExceptions());
			usecase.setPriority(usecaseData.getPriority());
			usecase.setFrequencyOfUse(usecaseData.getFrequencyOfUse());
			usecase.setBussinessRules(usecaseData.getBussinessRules());
			usecase.setOtherInformations(usecaseData.getOtherInformations());
			usecase.setAssumptions(usecaseData.getAssumptions());
			usecaseDao.update(usecase);
		}
		return usecase;
	}
	@Override
	public Usecase updateActivityDiagram(int usecaseId, String activityDiagram) {
		Usecase usecase = usecaseDao.getById(usecaseId);
		if (usecase != null)
		{
			usecase.setActivityDiagram(activityDiagram);
			usecaseDao.update(usecase);
		}
		return usecase;
	}
	@Override
	public Usecase updateSequenceDiagram(int usecaseId, String sequenceDiagram) {
		Usecase usecase = usecaseDao.getById(usecaseId);
		if (usecase != null)
		{
			usecase.setSequenceDiagram(sequenceDiagram);
			usecaseDao.update(usecase);
		}
		return usecase;
	}
	@Override
	public String testActivityDiagram(String rootPath, int usecaseId, String activityDiagram) {
		String actURI = null;
		Usecase usecase = usecaseDao.getById(usecaseId);
		if (usecase != null) {
			Domain domain = usecase.getDomain();
			if (domain != null) {
				Project project = domain.getProject();
				if (project != null) {
					actURI = "/PM" + DiagramUtil.createDiagramImage(rootPath, project.getId(),
							domain.getId(), activityDiagram, DiagramType.ACTIVITY);
				}
			}
		}
		return actURI;
	}
	@Override
	public String testSequenceDiagram(String rootPath, int usecaseId, String sequenceDiagram) {
		String seqURI = null;
		Usecase usecase = usecaseDao.getById(usecaseId);
		if (usecase != null) {
			Domain domain = usecase.getDomain();
			if (domain != null) {
				Project project = domain.getProject();
				if (project != null) {
					seqURI = "/PM" + DiagramUtil.createDiagramImage(rootPath, project.getId(),
							domain.getId(), sequenceDiagram, DiagramType.SEQUENCE);
				}
			}
		}
		return seqURI;
	}
	@Override
	public Usecase updateName(int usecaseId, String name) {
		Usecase usecase = usecaseDao.getById(usecaseId);
		if (usecase != null)
		{
			usecase.setName(name);
			usecaseDao.update(usecase);
		}
		return usecase;
	}
}
