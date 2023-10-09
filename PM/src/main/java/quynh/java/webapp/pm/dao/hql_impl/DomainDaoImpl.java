package quynh.java.webapp.pm.dao.hql_impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import quynh.java.webapp.pm.dao.DomainDao;
import quynh.java.webapp.pm.model.Domain;
import quynh.java.webapp.pm.model.Project;
import quynh.java.webapp.pm.util.db.HibernateConnection;

public class DomainDaoImpl implements DomainDao {
private SessionFactory sessionFactory = HibernateConnection.getSessionFactory();
    
    public List<Domain> getAll(Project project) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query<Domain> query = session.createQuery("FROM Domain WHERE project=:project");
        query.setParameter("project", project);
        List<Domain> domains = query.list();
        session.getTransaction().commit();
        session.close();
        return domains;
    }
    public Domain get1(String name, Project project) {
        Session session = sessionFactory.openSession();
        Query<Domain> query = session.createQuery("FROM Domain WHERE name=:name and project=:project");
        query.setParameter("name", name);
        query.setParameter("project", project);
        Domain p = query.getSingleResultOrNull();
        session.close();
        return p;
    }
    public Domain getById(int id) {
        Session session = sessionFactory.openSession();
        Domain p = session.get(Domain.class, id);
        session.close();
        return p;
    }
    public Domain add(Domain domain) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(domain);
        session.getTransaction().commit();
        session.close();
        return domain;
    }
    public Domain update(Domain domain) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.update(domain);
        session.getTransaction().commit();
        session.close();
        return domain;
    }
    public Domain delete(Domain domain) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.delete(domain);
        session.getTransaction().commit();
        session.close();   
        return domain;
    }
}   
