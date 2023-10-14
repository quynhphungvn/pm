package quynh.java.webapp.pm.service;

import java.util.List;

import quynh.java.webapp.pm.model.ClassPackage;
import quynh.java.webapp.pm.model.ClassSpec;

public interface ClassSpecService {

	ClassSpec getById(int classId);

	List<ClassSpec> getAll(ClassPackage selectedPackage);

	ClassSpec add(String className, int packageId);

	ClassSpec updateNewName(String newName, int classId);

	ClassSpec delete(int classSpecId);

	ClassSpec updateClassSpec(String content, int classId);

}
