package by.iba.markovsky.festivalorganisation.util.database;

import by.iba.markovsky.festivalorganisation.configuration.DatabaseConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static HibernateUtil ourInstance = new HibernateUtil();
    private static final SessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new Configuration().mergeProperties(DatabaseConfiguration.getProperties())
                    .configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    private HibernateUtil() {
    }

    public static HibernateUtil getInstance() {
        return ourInstance;
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static void closeSessionFactory(){
        sessionFactory.close();
    }

}
