package quynh.java.webapp.pm.dao.hql_impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import quynh.java.webapp.pm.dao.ClassSpecDao;
import quynh.java.webapp.pm.model.ClassSpec;
import quynh.java.webapp.pm.util.db.HibernateConnection;
import quynh.java.webapp.pm.model.ClassPackage;

public class ClassSpecDaoImpl implements ClassSpecDao {
private SessionFactory sessionFactory = HibernateConnection.getSessionFactory();
    
    public List<ClassSpec> getAll(ClassPackage classPackage) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query<ClassSpec> query = session.createQuery("FROM ClassSpec WHERE classPackage=:classPackage");
        query.setParameter("classPackage", classPackage);
        List<ClassSpec> classSpecifications = query.list();
        session.getTransaction().commit();
        session.close();
        return classSpecifications;
    }
    public ClassSpec get1(String name, ClassPackage classPackage) {
        Session session = sessionFactory.openSession();
        Query<ClassSpec> query = session.createQuery("FROM ClassSpec WHERE name=:name and classPackage=:classPackage");
        query.setParameter("name", name);
        query.setParameter("classPackage", classPackage);
        ClassSpec p = query.getSingleResultOrNull();
        session.close();
        return p;
    }
    public ClassSpec getById(int id) {
        Session session = sessionFactory.openSession();
        ClassSpec p = session.get(ClassSpec.class, id);
        session.close();
        return p;
    }
    public ClassSpec add(ClassSpec classSpecification) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(classSpecification);
        session.getTransaction().commit();
        session.close();
        return classSpecification;
    }
    public ClassSpec update(ClassSpec classSpecification) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.update(classSpecification);
        session.getTransaction().commit();
        session.close();
        return classSpecification;
    }
    public ClassSpec delete(ClassSpec classSpecification) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.delete(classSpecification);
        session.getTransaction().commit();
        session.close();   
        return classSpecification;
    }
}
