package quynh.java.webapp.pm.service.impl;

import java.util.List;

import quynh.java.webapp.pm.dao.ClassSpecDao;
import quynh.java.webapp.pm.dao.TestingFunctionDao;
import quynh.java.webapp.pm.dao.hql_impl.ClassSpecDaoImpl;
import quynh.java.webapp.pm.dao.hql_impl.TestingFunctionDaoImpl;
import quynh.java.webapp.pm.model.ClassSpec;
import quynh.java.webapp.pm.model.TestingFunction;
import quynh.java.webapp.pm.service.TestingFunctionService;

public class TestingFunctionServiceImpl implements TestingFunctionService{
	private TestingFunctionDao testingFunctionDao = new TestingFunctionDaoImpl();
	private ClassSpecDao classSpecDao = new ClassSpecDaoImpl();
	@Override
	public TestingFunction getById(int testId) {
		return testingFunctionDao.getById(testId);
	}

	@Override
	public List<TestingFunction> getAll(ClassSpec selectedClass) {
		return testingFunctionDao.getAll(selectedClass);
	}

	@Override
	public TestingFunction add(String testName, String testRequirement, String functionParams,
			String functionReturn, String functionExceptions, String functionLogs, int classId) {
		ClassSpec cls = classSpecDao.getById(classId);
		TestingFunction tf = null;
		if (cls != null) {
			tf = new TestingFunction();
			tf.setName(testName);
			tf.setTestRequirement(testRequirement);
			tf.setFunctionParams(functionParams);
			tf.setFunctionReturn(functionReturn);
			tf.setFunctionExceptions(functionExceptions);
			tf.setFunctionLogs(functionLogs);
			tf.setClassSpec(cls);
			testingFunctionDao.add(tf);
		}
		return tf;
	}

	@Override
	public TestingFunction updateInfo(String newName, String testRequirement, String functionParams,
			String functionReturn, String functionExceptions, String functionLogs, int testId) {
		TestingFunction tf = testingFunctionDao.getById(testId);
		if (tf != null) {
			if (newName != null && newName.isBlank() == false)
				tf.setName(newName);
			tf.setTestRequirement(testRequirement);
			tf.setFunctionParams(functionParams);
			tf.setFunctionReturn(functionReturn);
			tf.setFunctionExceptions(functionExceptions);
			tf.setFunctionLogs(functionLogs);
			testingFunctionDao.update(tf);
		}
		return tf;
	}

	@Override
	public TestingFunction updatePlanInput(String planInput, int testId) {
		TestingFunction tf = testingFunctionDao.getById(testId);
		if (tf != null) {
			tf.setTestPlanInput(planInput);
			testingFunctionDao.update(tf);
		}
		return tf;
	}
	@Override
	public TestingFunction updatePlanResult(String planResult, int testId) {
		TestingFunction tf = testingFunctionDao.getById(testId);
		if (tf != null) {
			tf.setTestPlanResult(planResult);
			testingFunctionDao.update(tf);
		}
		return tf;
	}
	@Override
	public TestingFunction delete(int testId) {
		TestingFunction tf = testingFunctionDao.getById(testId);
		if (tf != null)
			testingFunctionDao.delete(tf);
		return tf;
	}

}
