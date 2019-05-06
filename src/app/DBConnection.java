package app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;



public class DBConnection {

    public static Session getSession() {
        return session;
    }

    private static Session session;
    private static SessionFactory sessionFactory;

    public static void connect()
    {
        sessionFactory = new Configuration()
                .configure("/app/hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
                .buildSessionFactory();


        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    //  Close the connection....
    public static void disconnect()
    {
        session.close();
        sessionFactory.close();

    }

    public static Query executeQuery(String hql)
    {
        return session.createQuery(hql);

    }

    public static void executeUpdate(String hql)
    {
        Query query;
        query = DBConnection.getSession().createSQLQuery(hql);

        query.executeUpdate();

    }

}