package quynh.java.webapp.pm.service.impl;

import java.util.List;

import quynh.java.webapp.pm.dao.ClassPackageDao;
import quynh.java.webapp.pm.dao.DomainDao;
import quynh.java.webapp.pm.dao.hql_impl.ClassPackageDaoImpl;
import quynh.java.webapp.pm.dao.hql_impl.DomainDaoImpl;
import quynh.java.webapp.pm.model.ClassPackage;
import quynh.java.webapp.pm.model.Domain;
import quynh.java.webapp.pm.service.ClassPackageService;

public class ClassPackageServiceImpl implements ClassPackageService{
	private ClassPackageDao classPackageDao = new ClassPackageDaoImpl();
	private DomainDao domainDao = new DomainDaoImpl();
	@Override
	public ClassPackage getById(int packageId) {
		return classPackageDao.getById(packageId);
	}

	@Override
	public List<ClassPackage> getAll(Domain selectedDomain) {
		return classPackageDao.getAll(selectedDomain);
	}

	@Override
	public ClassPackage add(String packageName, String info, int domainId) {
		Domain domain = domainDao.getById(domainId);
		ClassPackage pkg = null;
		if (domain != null) {
			pkg = new ClassPackage();
			pkg.setName(packageName);
			pkg.setDomain(domain);
			pkg.setInfo(info);
			classPackageDao.add(pkg);
		}
		return pkg;
	}

	@Override
	public ClassPackage updateInfo(String newName, String info, int packageId) {
		ClassPackage pkg = classPackageDao.getById(packageId);
		if (pkg != null) {
			if (newName != null && newName.isBlank() == false) {
				pkg.setName(newName);
			}
			pkg.setInfo(info);
		}
		classPackageDao.update(pkg);
		return pkg;
	}

	@Override
	public ClassPackage delete(int classPackageId) {
		ClassPackage classPackage = classPackageDao.getById(classPackageId);
		if (classPackage != null) {
			classPackageDao.delete(classPackage);
		}
		return classPackage;
	}

}
