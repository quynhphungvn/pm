package quynh.java.webapp.pm.dao.hql_impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import quynh.java.webapp.pm.dao.PlanDiagramDao;
import quynh.java.webapp.pm.model.PlanDiagram;
import quynh.java.webapp.pm.util.db.HibernateConnection;
import quynh.java.webapp.pm.model.Domain;

public class PlanDiagramDaoImpl implements PlanDiagramDao{
	private SessionFactory sessionFactory = HibernateConnection.getSessionFactory();
	
	public List<PlanDiagram> getAll(Domain domain) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query<PlanDiagram> query = session.createQuery("FROM PlanDiagram WHERE domain=:domain");
        query.setParameter("domain", domain);
        List<PlanDiagram> planDiagrams = query.list();
        session.getTransaction().commit();
        session.close();
        return planDiagrams;
    }
    public PlanDiagram get1(String name, Domain domain) {
        Session session = sessionFactory.openSession();
        Query<PlanDiagram> query = session.createQuery("FROM PlanDiagram WHERE name=:name and domain=:domain");
        query.setParameter("name", name);
        query.setParameter("domain", domain);
        PlanDiagram p = query.getSingleResultOrNull();
        session.close();
        return p;
    }
    public PlanDiagram getById(int id) {
        Session session = sessionFactory.openSession();
        PlanDiagram p = session.get(PlanDiagram.class, id);
        session.close();
        return p;
    }
    public PlanDiagram add(PlanDiagram planDiagram) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(planDiagram);
        session.getTransaction().commit();
        session.close();
        return planDiagram;
    }
    public PlanDiagram update(PlanDiagram planDiagram) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.update(planDiagram);
        session.getTransaction().commit();
        session.close();
        return planDiagram;
    }
    public PlanDiagram delete(PlanDiagram planDiagram) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.delete(planDiagram);
        session.getTransaction().commit();
        session.close();   
        return planDiagram;
    }
}
