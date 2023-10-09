package quynh.java.webapp.pm.dao;

import java.util.List;

import quynh.java.webapp.pm.model.PlanDiagram;
import quynh.java.webapp.pm.model.Domain;

public interface PlanDiagramDao {
	List<PlanDiagram> getAll(Domain domain);
    PlanDiagram getById(int id);
    PlanDiagram add(PlanDiagram planDiagram);
    PlanDiagram update(PlanDiagram planDiagram);
    PlanDiagram delete(PlanDiagram planDiagram);
}
