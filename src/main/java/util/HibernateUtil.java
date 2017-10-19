package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

	private static SessionFactory sSessionFactory;

	private static final Object mLock = new Object();

	public static SessionFactory createSessionFactory() {
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
				.configure(HibernateUtil.class.getClassLoader().getResource("hibernate.cfg.xml")).build();
		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		return metaData.getSessionFactoryBuilder().build();
	}

	public static SessionFactory getSessionFactory() {
		if (sSessionFactory == null) {
			synchronized (mLock) {
				if (sSessionFactory == null) {
					sSessionFactory = createSessionFactory();
				}
			}
		}
		return sSessionFactory;
	}
}
