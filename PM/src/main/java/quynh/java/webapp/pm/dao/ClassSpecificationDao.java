package quynh.java.webapp.pm.dao;

import java.util.List;

import quynh.java.webapp.pm.model.ClassPackage;
import quynh.java.webapp.pm.model.ClassSpecification;

public interface ClassSpecificationDao {
    
    List<ClassSpecification> getAll(ClassPackage classPackage);
    ClassSpecification get1(int id);
    ClassSpecification get1(String name, ClassPackage classPackage);
    ClassSpecification add(ClassSpecification classSpecification);
    ClassSpecification update(ClassSpecification classSpecification);
    ClassSpecification delete(ClassSpecification classSpecification);
}
