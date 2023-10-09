package quynh.java.webapp.pm.dao.hql_impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import quynh.java.webapp.pm.dao.ClassPackageDao;
import quynh.java.webapp.pm.model.ClassPackage;
import quynh.java.webapp.pm.model.Domain;
import quynh.java.webapp.pm.util.db.HibernateConnection;

public class ClassPackageDaoImpl implements ClassPackageDao {
private SessionFactory sessionFactory = HibernateConnection.getSessionFactory();
    
    public List<ClassPackage> getAll(Domain domain) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query<ClassPackage> query = session.createQuery("FROM ClassPackage WHERE domain=:domain");
        query.setParameter("domain", domain);
        List<ClassPackage> classPackages = query.list();
        session.getTransaction().commit();
        session.close();
        return classPackages;
    }
    public ClassPackage get1(String name, Domain domain) {
        Session session = sessionFactory.openSession();
        Query<ClassPackage> query = session.createQuery("FROM ClassPackage WHERE name=:name and domain=:domain");
        query.setParameter("name", name);
        query.setParameter("domain", domain);
        ClassPackage p = query.getSingleResultOrNull();
        session.close();
        return p;
    }
    public ClassPackage get1(int id) {
        Session session = sessionFactory.openSession();
        ClassPackage p = session.get(ClassPackage.class, id);
        session.close();
        return p;
    }
    public ClassPackage add(ClassPackage classPackage) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(classPackage);
        session.getTransaction().commit();
        session.close();
        return classPackage;
    }
    public ClassPackage update(ClassPackage classPackage) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.update(classPackage);
        session.getTransaction().commit();
        session.close();
        return classPackage;
    }
    public ClassPackage delete(ClassPackage classPackage) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.delete(classPackage);
        session.getTransaction().commit();
        session.close();   
        return classPackage;
    }
}
