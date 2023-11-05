package com.web3.web3.db;

import com.web3.web3.model.ResultDataBean;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class JPAUtils {
    private static final EntityManagerFactory factory;

    static {
        try {
            Properties info = new Properties();
            info.load(JPAUtils.class.getClassLoader().getResourceAsStream("/db.cfg"));
            factory = new Configuration().configure()
                    .setProperty(AvailableSettings.USER,
                            info.getProperty("user"))
                    .setProperty(AvailableSettings.PASS,
                            info.getProperty("password"))
                    .addAnnotatedClass(ResultDataBean.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Something went wrong during initializing EclipseLink: " + ex);
            throw new ExceptionInInitializerError();
        }
    }

    public static EntityManagerFactory getFactory() {
        return factory;
    }
}
