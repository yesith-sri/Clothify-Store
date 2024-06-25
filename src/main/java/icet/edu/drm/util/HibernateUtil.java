package icet.edu.drm.util;

import icet.edu.drm.entity.CustomerEntity;
import icet.edu.drm.entity.ProductEntity;
import icet.edu.drm.entity.SupplierEntity;
import icet.edu.drm.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static SessionFactory session = createSession();

    private static SessionFactory createSession() {
        StandardServiceRegistry build = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(build)
                .addAnnotatedClass(UserEntity.class)
                .addAnnotatedClass(CustomerEntity.class)
                .addAnnotatedClass(SupplierEntity.class)
                .addAnnotatedClass(ProductEntity.class)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();

        return metadata.getSessionFactoryBuilder().build();
    }
    public static Session getSession(){
        return session.openSession();
    }
}
