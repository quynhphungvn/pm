package quynh.java.webapp.pm.dao.hql_impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


import quynh.java.webapp.pm.dao.ProjectDao;
import quynh.java.webapp.pm.model.Project;
import quynh.java.webapp.pm.util.db.HibernateConnection;

public class ProjectDaoImpl implements ProjectDao{
    private SessionFactory sessionFactory = HibernateConnection.getSessionFactory();
    
    public List<Project> getAll() {
        Session session = sessionFactory.openSession();
        Query<Project> query = session.createQuery("FROM Project");
        List<Project> projects = query.list();
        session.close();
        return projects;
    }
    public Project getByName(String name) {
        Session session = sessionFactory.openSession();
        Query<Project> query = session.createQuery("FROM Project WHERE name=:name");
        query.setParameter("name", name);
        Project p = query.getSingleResultOrNull();
        session.close();
        return p;
    }
    public Project getById(int id) {
        Session session = sessionFactory.openSession();
        Project p = session.get(Project.class, id);
        session.close();
        return p;
    }
    public Project add(Project project) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(project);
        session.getTransaction().commit();
        session.close();
        return project;
    }
    public Project update(Project project) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.update(project);
        session.getTransaction().commit();
        session.close();
        return project;
    }
    public Project delete(Project project) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.delete(project);
        session.getTransaction().commit();
        session.close();   
        return project;
    }

}
