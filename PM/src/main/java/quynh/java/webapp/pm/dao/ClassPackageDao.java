package quynh.java.webapp.pm.dao;

import java.util.List;

import quynh.java.webapp.pm.model.ClassPackage;
import quynh.java.webapp.pm.model.Domain;

public interface ClassPackageDao {
    
    List<ClassPackage> getAll(Domain domain);
    List<ClassPackage> getAllAndJustOnePackageHasClasses(Domain domain, int packageId);
    ClassPackage getById(int id);
    ClassPackage get1(String name, Domain domain);
    ClassPackage add(ClassPackage classPackage);
    ClassPackage update(ClassPackage classPackage);
    ClassPackage delete(ClassPackage classPackage);
}
