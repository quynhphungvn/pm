package quynh.java.webapp.pm.dao.hql_impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import quynh.java.webapp.pm.dao.ClassSpecificationDao;
import quynh.java.webapp.pm.model.ClassSpecification;
import quynh.java.webapp.pm.util.db.HibernateConnection;
import quynh.java.webapp.pm.model.ClassPackage;

public class ClassSpecificationDaoImpl implements ClassSpecificationDao {
private SessionFactory sessionFactory = HibernateConnection.getSessionFactory();
    
    public List<ClassSpecification> getAll(ClassPackage classPackage) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query<ClassSpecification> query = session.createQuery("FROM ClassSpecification WHERE classPackage=:classPackage");
        query.setParameter("classPackage", classPackage);
        List<ClassSpecification> classSpecifications = query.list();
        session.getTransaction().commit();
        session.close();
        return classSpecifications;
    }
    public ClassSpecification get1(String name, ClassPackage classPackage) {
        Session session = sessionFactory.openSession();
        Query<ClassSpecification> query = session.createQuery("FROM ClassSpecification WHERE name=:name and classPackage=:classPackage");
        query.setParameter("name", name);
        query.setParameter("classPackage", classPackage);
        ClassSpecification p = query.getSingleResultOrNull();
        session.close();
        return p;
    }
    public ClassSpecification get1(int id) {
        Session session = sessionFactory.openSession();
        ClassSpecification p = session.get(ClassSpecification.class, id);
        session.close();
        return p;
    }
    public ClassSpecification add(ClassSpecification classSpecification) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(classSpecification);
        session.getTransaction().commit();
        session.close();
        return classSpecification;
    }
    public ClassSpecification update(ClassSpecification classSpecification) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.update(classSpecification);
        session.getTransaction().commit();
        session.close();
        return classSpecification;
    }
    public ClassSpecification delete(ClassSpecification classSpecification) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.delete(classSpecification);
        session.getTransaction().commit();
        session.close();   
        return classSpecification;
    }
}
