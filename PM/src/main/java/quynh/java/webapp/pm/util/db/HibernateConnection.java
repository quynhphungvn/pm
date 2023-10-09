package quynh.java.webapp.pm.util.db;

import java.io.IOException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import quynh.java.webapp.pm.model.ClassPackage;
import quynh.java.webapp.pm.model.ClassSpecification;
import quynh.java.webapp.pm.model.DesignImage;
import quynh.java.webapp.pm.model.Domain;
import quynh.java.webapp.pm.model.PlanDiagram;
import quynh.java.webapp.pm.model.Project;
import quynh.java.webapp.pm.model.Screen;
import quynh.java.webapp.pm.model.SqlQuery;
import quynh.java.webapp.pm.model.UnitTesting;
import quynh.java.webapp.pm.model.Usecase;
import quynh.java.webapp.pm.model.UsecaseSpecification;

public class HibernateConnection {
private final static SessionFactory FACTORY;
    
static {
    Configuration conf = new Configuration();
    Properties props = new Properties();
    try {
        props.load(HibernateConnection.class.getClassLoader().getResourceAsStream("db-info.properties"));
    } catch (IOException e) {

        e.printStackTrace();
    }
    conf.setProperty(Environment.DIALECT, props.getProperty("DIALECT"));
    conf.setProperty(Environment.DRIVER, props.getProperty("DRIVER"));
    conf.setProperty(Environment.URL, props.getProperty("URL"));
    conf.setProperty(Environment.USER, props.getProperty("USER"));
    conf.setProperty(Environment.PASS, props.getProperty("PASSWORD"));
    conf.setProperty(Environment.SHOW_SQL, "true");

    // knowledge
    conf.addAnnotatedClass(Project.class);
    conf.addAnnotatedClass(Domain.class);
    conf.addAnnotatedClass(PlanDiagram.class);
    conf.addAnnotatedClass(Screen.class);
    conf.addAnnotatedClass(ClassPackage.class);
    conf.addAnnotatedClass(ClassSpecification.class);
    conf.addAnnotatedClass(SqlQuery.class);
    conf.addAnnotatedClass(UnitTesting.class);
    conf.addAnnotatedClass(Usecase.class);
    conf.addAnnotatedClass(UsecaseSpecification.class);
    conf.addAnnotatedClass(DesignImage.class);
    ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
    FACTORY = conf.buildSessionFactory(registry);
}
    
    public static SessionFactory getSessionFactory() {
        return FACTORY;
    }
}
