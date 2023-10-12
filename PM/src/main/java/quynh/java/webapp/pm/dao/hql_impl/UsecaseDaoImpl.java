package quynh.java.webapp.pm.dao.hql_impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import quynh.java.webapp.pm.model.Usecase;
import quynh.java.webapp.pm.util.db.HibernateConnection;
import quynh.java.webapp.pm.dao.UsecaseDao;
import quynh.java.webapp.pm.model.Domain;
import quynh.java.webapp.pm.model.Screen;

public class UsecaseDaoImpl implements UsecaseDao {
private SessionFactory sessionFactory = HibernateConnection.getSessionFactory();
    
    public List<Usecase> getAll(Domain domain) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query<Usecase> query = session.createQuery("FROM Usecase WHERE domain=:domain");
        query.setParameter("domain", domain);
        List<Usecase> usecases = query.list();
        session.getTransaction().commit();
        session.close();
        return usecases;
    }
    public Usecase getById(int id) {
        Session session = sessionFactory.openSession();
        Usecase p = session.get(Usecase.class, id);
        session.close();
        return p;
    }
    public Usecase add(Usecase usecase) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(usecase);
        session.getTransaction().commit();
        session.close();
        return usecase;
    }
    public Usecase update(Usecase usecase) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.update(usecase);
        session.getTransaction().commit();
        session.close();
        return usecase;
    }
    public Usecase delete(Usecase usecase) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.delete(usecase);
        session.getTransaction().commit();
        session.close();   
        return usecase;
    }
}
