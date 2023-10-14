package quynh.java.webapp.pm.dao.hql_impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import quynh.java.webapp.pm.dao.TestingFunctionDao;
import quynh.java.webapp.pm.model.ClassSpec;
import quynh.java.webapp.pm.model.TestingFunction;
import quynh.java.webapp.pm.util.db.HibernateConnection;

public class TestingFunctionDaoImpl implements TestingFunctionDao {
	private SessionFactory sessionFactory = HibernateConnection.getSessionFactory();
	
	public List<TestingFunction> getAll(ClassSpec classSpec) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query<TestingFunction> query = session.createQuery("FROM TestingFunction WHERE classSpec=:classSpec");
        query.setParameter("classSpec", classSpec);
        List<TestingFunction> testingFunctions = query.list();
        session.getTransaction().commit();
        session.close();
        return testingFunctions;
    }
    public TestingFunction getById(int id) {
        Session session = sessionFactory.openSession();
        TestingFunction p = session.get(TestingFunction.class, id);
        session.close();
        return p;
    }
    public TestingFunction add(TestingFunction testingFunction) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(testingFunction);
        session.getTransaction().commit();
        session.close();
        return testingFunction;
    }
    public TestingFunction update(TestingFunction testingFunction) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.update(testingFunction);
        session.getTransaction().commit();
        session.close();
        return testingFunction;
    }
    public TestingFunction delete(TestingFunction testingFunction) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.delete(testingFunction);
        session.getTransaction().commit();
        session.close();   
        return testingFunction;
    }

}
