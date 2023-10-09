package quynh.java.webapp.pm.service;

import java.util.List;

import quynh.java.webapp.pm.model.PlanDiagram;

public interface PlanDiagramService {

	PlanDiagram getById(int diagramId);

	List<PlanDiagram> getAll(int id);

	PlanDiagram add(String planDiagramName, String planDiagramType, int domainId);

	PlanDiagram updateName(int diagramId, String newName);

	PlanDiagram updateText(int diagramId, String diagramText);

	PlanDiagram delete(int diagramId);

}
