package quynh.java.webapp.pm.dao.hql_impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import quynh.java.webapp.pm.dao.SqlQueryDao;
import quynh.java.webapp.pm.model.SqlQuery;
import quynh.java.webapp.pm.util.db.HibernateConnection;
import quynh.java.webapp.pm.model.Domain;

public class SqlQueryDaoImpl implements SqlQueryDao {
private SessionFactory sessionFactory = HibernateConnection.getSessionFactory();
    
    public List<SqlQuery> getAll(Domain domain) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query<SqlQuery> query = session.createQuery("FROM SqlQuery WHERE domain=:domain");
        query.setParameter("domain", domain);
        List<SqlQuery> sqlQuerys = query.list();
        session.getTransaction().commit();
        session.close();
        return sqlQuerys;
    }
    public SqlQuery get1(String name, Domain domain) {
        Session session = sessionFactory.openSession();
        Query<SqlQuery> query = session.createQuery("FROM SqlQuery WHERE name=:name and domain=:domain");
        query.setParameter("name", name);
        query.setParameter("domain", domain);
        SqlQuery p = query.getSingleResultOrNull();
        session.close();
        return p;
    }
    public SqlQuery getById(int id) {
        Session session = sessionFactory.openSession();
        SqlQuery p = session.get(SqlQuery.class, id);
        session.close();
        return p;
    }
    public SqlQuery add(SqlQuery sqlQuery) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(sqlQuery);
        session.getTransaction().commit();
        session.close();
        return sqlQuery;
    }
    public SqlQuery update(SqlQuery sqlQuery) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.update(sqlQuery);
        session.getTransaction().commit();
        session.close();
        return sqlQuery;
    }
    public SqlQuery delete(SqlQuery sqlQuery) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.delete(sqlQuery);
        session.getTransaction().commit();
        session.close();   
        return sqlQuery;
    }
    

}
