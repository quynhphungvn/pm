package quynh.java.webapp.pm.service;

import java.util.List;

import quynh.java.webapp.pm.model.ClassPackage;
import quynh.java.webapp.pm.model.Domain;

public interface ClassPackageService {

	ClassPackage getById(int packageId);

	List<ClassPackage> getAll(Domain selectedDomain);

	ClassPackage add(String packageName, String info, int domainId);

	ClassPackage updateInfo(String newName, String info, int packageId);

	ClassPackage delete(int classPackageId);

}
