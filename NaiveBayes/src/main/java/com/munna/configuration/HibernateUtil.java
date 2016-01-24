package com.munna.configuration;

import com.munna.model.GenuineData;
import com.munna.model.NormalizedData;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Created by monju on 1/24/2016.
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static SessionFactory buildSessionFactory(){
        try{
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(GenuineData.class);
            configuration.addAnnotatedClass(NormalizedData.class);

            return configuration.buildSessionFactory(new StandardServiceRegistryBuilder().build());
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("There was an error while building");
        }
    }
}
