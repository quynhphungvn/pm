package quynh.java.webapp.pm.dao.hql_impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import quynh.java.webapp.pm.model.Screen;
import quynh.java.webapp.pm.util.db.HibernateConnection;
import quynh.java.webapp.pm.dao.ScreenDao;
import quynh.java.webapp.pm.model.Domain;

public class ScreenDaoImpl implements ScreenDao {
private SessionFactory sessionFactory = HibernateConnection.getSessionFactory();
    
    public List<Screen> getAll(Domain domain) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query<Screen> query = session.createQuery("FROM Screen WHERE domain=:domain");
        query.setParameter("domain", domain);
        List<Screen> screens = query.list();
        session.getTransaction().commit();
        session.close();
        return screens;
    }
    public Screen get1(String name, Domain domain) {
        Session session = sessionFactory.openSession();
        Query<Screen> query = session.createQuery("FROM Screen WHERE name=:name and domain=:domain");
        query.setParameter("name", name);
        query.setParameter("domain", domain);
        Screen p = query.getSingleResultOrNull();
        session.close();
        return p;
    }
    public Screen getById(int id) {
        Session session = sessionFactory.openSession();
        Screen p = session.get(Screen.class, id);
        session.close();
        return p;
    }
    public Screen add(Screen screen) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(screen);
        session.getTransaction().commit();
        session.close();
        return screen;
    }
    public Screen update(Screen screen) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.update(screen);
        session.getTransaction().commit();
        session.close();
        return screen;
    }
    public Screen delete(Screen screen) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.delete(screen);
        session.getTransaction().commit();
        session.close();   
        return screen;
    }
}   
