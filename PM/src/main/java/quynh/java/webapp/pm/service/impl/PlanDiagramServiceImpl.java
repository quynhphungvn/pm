package quynh.java.webapp.pm.service.impl;

import java.util.List;

import quynh.java.webapp.pm.dao.DomainDao;
import quynh.java.webapp.pm.dao.PlanDiagramDao;
import quynh.java.webapp.pm.dao.hql_impl.DomainDaoImpl;
import quynh.java.webapp.pm.dao.hql_impl.PlanDiagramDaoImpl;
import quynh.java.webapp.pm.model.Domain;
import quynh.java.webapp.pm.model.PlanDiagram;
import quynh.java.webapp.pm.service.PlanDiagramService;
import quynh.java.webapp.pm.util.plantuml.DiagramUtil;

public class PlanDiagramServiceImpl implements PlanDiagramService {
	private DomainDao domainDao = new DomainDaoImpl();
	private PlanDiagramDao planDiagramDao = new PlanDiagramDaoImpl();
	@Override
	public PlanDiagram getById(int diagramId) {
		return planDiagramDao.getById(diagramId);
	}

	@Override
	public List<PlanDiagram> getAll(int domainId) {
		List<PlanDiagram> listPlanDiagram = null;
		Domain domain = domainDao.getById(domainId);
		if (domain != null)
			listPlanDiagram = planDiagramDao.getAll(domain);
		return listPlanDiagram;
	}

	@Override
	public PlanDiagram add(String planDiagramName, String planDiagramType, int domainId) {
		Domain domain = null;
		PlanDiagram planDiagram = null;
		if (domainId != 0) {
			domain = domainDao.getById(domainId);
			if (planDiagramName.isBlank() == false)
			{
				planDiagram = new PlanDiagram();
				planDiagram.setName(planDiagramName);
				planDiagram.setDomain(domain);
				planDiagram.setType(planDiagramType);
				planDiagram.setDiagram(DiagramUtil.getInitText(planDiagramType));
				planDiagramDao.add(planDiagram);
			}
		}
		return planDiagram;
	}

	@Override
	public PlanDiagram updateName(int diagramId, String newName) {
		PlanDiagram pd = planDiagramDao.getById(diagramId);
		if (pd != null && newName.isBlank() == false)
			pd.setName(newName);
		return planDiagramDao.update(pd);
	}

	@Override
	public PlanDiagram updateText(int diagramId, String diagramText) {
		PlanDiagram pd = planDiagramDao.getById(diagramId);
		if (pd != null)
			pd.setDiagram(diagramText);
		return planDiagramDao.update(pd);
	}

	@Override
	public PlanDiagram delete(int diagramId) {
		PlanDiagram pd = planDiagramDao.getById(diagramId);
		if (pd != null)
			planDiagramDao.delete(pd);
		return pd;
	}
	
}
