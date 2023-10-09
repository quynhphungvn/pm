package quynh.java.webapp.pm.dao.hql_impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import quynh.java.webapp.pm.dao.UsecaseSpecificationDao;
import quynh.java.webapp.pm.model.Usecase;
import quynh.java.webapp.pm.model.UsecaseSpecification;
import quynh.java.webapp.pm.util.db.HibernateConnection;

public class UsecaseSpecificationDaoImpl implements UsecaseSpecificationDao {
private SessionFactory sessionFactory = HibernateConnection.getSessionFactory();
    
    public UsecaseSpecification get1(Usecase usecase) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query<UsecaseSpecification> query = session.createQuery("FROM UsecaseSpecification WHERE usecase=:usecase");
        query.setParameter("usecase", usecase);
        UsecaseSpecification usecaseSpecification = query.getSingleResultOrNull();
        session.getTransaction().commit();
        session.close();
        return usecaseSpecification;
    }
    public UsecaseSpecification get1(int id) {
        Session session = sessionFactory.openSession();
        UsecaseSpecification p = session.get(UsecaseSpecification.class, id);
        session.close();
        return p;
    }
    public UsecaseSpecification add(UsecaseSpecification usecaseSpecification) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(usecaseSpecification);
        session.getTransaction().commit();
        session.close();
        return usecaseSpecification;
    }
    public UsecaseSpecification update(UsecaseSpecification usecaseSpecification) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.update(usecaseSpecification);
        session.getTransaction().commit();
        session.close();
        return usecaseSpecification;
    }
    public UsecaseSpecification delete(UsecaseSpecification usecaseSpecification) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.delete(usecaseSpecification);
        session.getTransaction().commit();
        session.close();   
        return usecaseSpecification;
    }
}
