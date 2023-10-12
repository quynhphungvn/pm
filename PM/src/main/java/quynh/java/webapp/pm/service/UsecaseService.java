package quynh.java.webapp.pm.service;

import java.util.List;

import quynh.java.webapp.pm.model.Domain;
import quynh.java.webapp.pm.model.Usecase;

public interface UsecaseService {

	List<Usecase> getAll(Domain selectedDomain);

	Usecase getById(int usecaseId);

	Usecase add(String name, int domainId);

	Usecase delete(int usecaseId);

	Usecase updateSpec(Usecase usecase);

	Usecase updateActivityDiagram(int usecaseId, String activityDiagram);

	Usecase updateSequenceDiagram(int usecaseId, String sequenceDiagram);

	String testActivityDiagram(String rootPath, int usecaseId, String activityDiagram);

	String testSequenceDiagram(String rootPath, int usecaseId, String sequenceDiagram);

	Usecase updateName(int usecaseId, String name);

}
