package quynh.java.webapp.pm.dao;

import java.util.List;

import quynh.java.webapp.pm.model.ClassPackage;
import quynh.java.webapp.pm.model.ClassSpec;

public interface ClassSpecDao {
    
    List<ClassSpec> getAll(ClassPackage classPackage);
    ClassSpec getById(int id);
    ClassSpec get1(String name, ClassPackage classPackage);
    ClassSpec add(ClassSpec classSpecification);
    ClassSpec update(ClassSpec classSpecification);
    ClassSpec delete(ClassSpec classSpecification);
}
