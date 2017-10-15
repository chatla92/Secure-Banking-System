package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory sSessionFactory;
	private static ServiceRegistry serviceRegistry;

	private static final Object mLock = new Object();

	public static SessionFactory createSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure(HibernateUtil.class.getClassLoader()
				.getResource("hibernate.cfg.xml"));
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
				configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
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
