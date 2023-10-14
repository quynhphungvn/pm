package quynh.java.webapp.pm.service.impl;

import java.util.List;

import quynh.java.webapp.pm.dao.ClassPackageDao;
import quynh.java.webapp.pm.dao.ClassSpecDao;
import quynh.java.webapp.pm.dao.hql_impl.ClassPackageDaoImpl;
import quynh.java.webapp.pm.dao.hql_impl.ClassSpecDaoImpl;
import quynh.java.webapp.pm.model.ClassPackage;
import quynh.java.webapp.pm.model.ClassSpec;
import quynh.java.webapp.pm.service.ClassSpecService;

public class ClassSpecServiceImpl implements ClassSpecService {
	ClassSpecDao classSpecDao = new ClassSpecDaoImpl();
	ClassPackageDao classPackageDao = new ClassPackageDaoImpl();
	
	@Override
	public ClassSpec getById(int classId) {
		return classSpecDao.getById(classId);
	}

	@Override
	public List<ClassSpec> getAll(ClassPackage selectedPackage) {
		return classSpecDao.getAll(selectedPackage);
	}

	@Override
	public ClassSpec add(String className, int packageId) {
		ClassPackage classPackage = classPackageDao.getById(packageId);
		ClassSpec clss = null;
		if (classPackage != null)
		{
			clss = new ClassSpec();
			clss.setName(className);
			clss.setClassPackage(classPackage);
			classSpecDao.add(clss);
		}
		return clss;
	}

	@Override
	public ClassSpec updateNewName(String newName, int classId) {
		ClassSpec cls = classSpecDao.getById(classId);
		if (cls != null) {
			cls.setName(newName);
			classSpecDao.update(cls);
		}
		return cls;
	}

	@Override
	public ClassSpec delete(int classSpecId) {
		ClassSpec classSpec = classSpecDao.getById(classSpecId);
		if (classSpec != null)
			classSpecDao.delete(classSpec);
		return classSpec;
	}

	@Override
	public ClassSpec updateClassSpec(String content, int classId) {
		ClassSpec cls = classSpecDao.getById(classId);
		if (cls != null) {
			cls.setDetailContent(content);
			classSpecDao.update(cls);
		}
		return cls;
	}

}
