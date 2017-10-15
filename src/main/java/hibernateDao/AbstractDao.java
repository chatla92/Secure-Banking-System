package hibernateDao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import util.HibernateUtil;

public class AbstractDao<T> {

	protected Class<T> clazz;
	protected final SessionFactory sessionFactory = HibernateUtil
			.getSessionFactory();

	public AbstractDao(Class<T> clazz) {
		this.clazz = clazz;
	}

	public Class<T> getClazz() {
		return this.clazz;
	}

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		List<T> entities = null;
		Session session = null;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from "
					+ this.clazz.getSimpleName() + " e");
			entities = (List<T>) query.list();
			session.getTransaction().commit();
		} catch (Exception exception) {
			if (session != null) {
				session.getTransaction().rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return entities;
	}

	@SuppressWarnings("unchecked")
	public T get(int id, String by) {
		T entity = null;
		Session session = null;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from "
					+ this.clazz.getSimpleName() + " e where e." + by
					+ " = :ID");
			query.setParameter("ID", id);
			entity = (T) query.uniqueResult();
			Hibernate.initialize(entity);
			session.getTransaction().commit();
		} catch (Exception exception) {
			if (session != null) {
				System.out.println("rolling back srini" + exception);
				session.getTransaction().rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return entity;
	}

	public boolean create(T entity) {
		Boolean success = false;
		Session session = null;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.persist(entity);
			session.getTransaction().commit();
			success = true;
		} catch (Exception exception) {
			if (session != null) {
				System.out.println("rolling back srini" + exception);
				session.getTransaction().rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return success;
	}

	public boolean update(T entity) {
		Boolean success = false;
		Session session = null;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.merge(entity);
			session.getTransaction().commit();
			success = true;
		} catch (Exception exception) {
			if (session != null) {
				session.getTransaction().rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return success;
	}

	public boolean delete(T entity) {
		Boolean success = false;
		Session session = null;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(entity);
			session.getTransaction().commit();
			success = true;
		} catch (Exception exception) {
			if (session != null) {
				session.getTransaction().rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return success;
	}

}
