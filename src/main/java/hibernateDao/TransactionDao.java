package hibernateDao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import hibernateModel.Transaction;

public class TransactionDao extends AbstractDao<Transaction> {

	public TransactionDao() {
		super(Transaction.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Transaction> getByType(String id) {
		List<Transaction> entities = null;
		Session session = null;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from " + this.clazz.getSimpleName() + " e where e.from_acc = :ID1 OR e.to_acc =:ID1");
			query.setParameter("ID1", id);
			entities = (List<Transaction>) query.list();
			session.getTransaction().commit();
		} catch (Exception exception) {
			if (session != null) {
				System.out.println("rolling back: ");
				exception.printStackTrace();
				session.getTransaction().rollback();
			}
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}

		return entities;
	}

	@SuppressWarnings("unchecked")
	public List<Transaction> getCriticalTrans() {
		List<Transaction> entities = null;
		Session session = null;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from " + this.clazz.getSimpleName()
					+ " e where e.iscritical is true AND e.transactionStatus is false AND e.internalUser = 0");
			entities = (List<Transaction>) query.list();
			session.getTransaction().commit();
		} catch (Exception exception) {
			if (session != null) {
				System.out.println("rolling back: ");
				exception.printStackTrace();
				session.getTransaction().rollback();
			}
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}

		return entities;
	}
}
