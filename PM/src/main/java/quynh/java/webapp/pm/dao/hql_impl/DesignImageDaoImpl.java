package quynh.java.webapp.pm.dao.hql_impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import quynh.java.webapp.pm.dao.DesignImageDao;
import quynh.java.webapp.pm.model.DesignImage;
import quynh.java.webapp.pm.model.Domain;
import quynh.java.webapp.pm.util.db.HibernateConnection;

public class DesignImageDaoImpl implements DesignImageDao {
	private SessionFactory sessionFactory = HibernateConnection.getSessionFactory();
    
    public List<DesignImage> getAll(Domain domain) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query<DesignImage> query = session.createQuery("FROM DesignImage WHERE domain=:domain");
        query.setParameter("domain", domain);
        List<DesignImage> designImages = query.list();
        session.getTransaction().commit();
        session.close();
        return designImages;
    }
    public DesignImage get1(String name, Domain domain) {
        Session session = sessionFactory.openSession();
        Query<DesignImage> query = session.createQuery("FROM DesignImage WHERE name=:name and domain=:domain");
        query.setParameter("name", name);
        query.setParameter("domain", domain);
        DesignImage p = query.getSingleResultOrNull();
        session.close();
        return p;
    }
    public DesignImage get1(int id) {
        Session session = sessionFactory.openSession();
        DesignImage p = session.get(DesignImage.class, id);
        session.close();
        return p;
    }
    public DesignImage add(DesignImage designImage) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(designImage);
        session.getTransaction().commit();
        session.close();
        return designImage;
    }
    public DesignImage update(DesignImage designImage) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.update(designImage);
        session.getTransaction().commit();
        session.close();
        return designImage;
    }
    public DesignImage delete(DesignImage designImage) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.delete(designImage);
        session.getTransaction().commit();
        session.close();   
        return designImage;
    }
}
