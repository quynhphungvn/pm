package quynh.java.webapp.pm.service;

import java.util.List;

import quynh.java.webapp.pm.model.ClassSpec;
import quynh.java.webapp.pm.model.TestingFunction;

public interface TestingFunctionService {

	TestingFunction getById(int testId);

	List<TestingFunction> getAll(ClassSpec selectedClass);

	TestingFunction add(String testName, String testRequirement, 
			String functionParams, String functionReturn,
			String functionExceptions, String functionLogs, int classId);

	TestingFunction updateInfo(String newName, String testRequirement, String functionParams, String functionReturn,
			String functionExceptions, String functionLogs, int testId);

	TestingFunction updatePlanInput(String planInput, int testId);

	TestingFunction delete(int testId);

	TestingFunction updatePlanResult(String planResult, int testId);

}
