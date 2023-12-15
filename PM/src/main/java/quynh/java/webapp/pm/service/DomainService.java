package quynh.java.webapp.pm.service;

import java.util.List;

import quynh.java.webapp.pm.model.Domain;

public interface DomainService {
    
    public List<Domain> getAll(int projectId);

	public Domain addDomain(String domainName, int projectId);

	public Domain getById(int domainId);

	public Domain updateDomain(int domainId, String newName, String overview, String scope, String technology, String note);

	public Domain deleteDomain(int domainId);

	public Domain updateRequirement(int domainId, String requirement);

	public Domain updateErd(int domainId, String erdDiagram);

	Domain updateClassDiagram(int domainId, String classDiagram);
    
}
