package quynh.java.webapp.pm.dao;

import quynh.java.webapp.pm.model.Usecase;
import quynh.java.webapp.pm.model.UsecaseSpecification;

public interface UsecaseSpecificationDao {
    UsecaseSpecification get1(Usecase usecase);
    UsecaseSpecification get1(int id);
    UsecaseSpecification add(UsecaseSpecification usecaseSpecification);
    UsecaseSpecification update(UsecaseSpecification usecaseSpecification);
    UsecaseSpecification delete(UsecaseSpecification usecaseSpecification);
}
