package quynh.java.webapp.pm.dao;

import java.util.List;

import quynh.java.webapp.pm.model.ClassSpecification;
import quynh.java.webapp.pm.model.UnitTesting;

public interface UnitTestingDao {
    List<UnitTesting> getAll(ClassSpecification classSpecification);
    UnitTesting get1(int id);
    UnitTesting get1(String name, ClassSpecification classSpecification);
    UnitTesting add(UnitTesting unitTesting);
    UnitTesting update(UnitTesting unitTesting);
    UnitTesting delete(UnitTesting unitTesting);
}
