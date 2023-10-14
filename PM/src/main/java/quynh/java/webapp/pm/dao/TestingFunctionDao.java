package quynh.java.webapp.pm.dao;

import java.util.List;

import quynh.java.webapp.pm.model.ClassSpec;
import quynh.java.webapp.pm.model.TestingFunction;

public interface TestingFunctionDao {
    List<TestingFunction> getAll(ClassSpec classSpec);
    TestingFunction getById(int id);
    TestingFunction add(TestingFunction testingFunction);
    TestingFunction update(TestingFunction testingFunction);
    TestingFunction delete(TestingFunction testingFunction);
}
