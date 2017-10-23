package hibernateDao;

import hibernateModel.ExternalAuthorization;
import hibernateModel.Transaction;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class ExternalAuthorizationDao extends
		AbstractDao<ExternalAuthorization> {

	public ExternalAuthorizationDao() {
		super(ExternalAuthorization.class);
		// TODO Auto-generated constructor stub
	}
	public List<Transaction> getByType(String id) {
		List<Transaction> entities = null;
		Session session = null;
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery(
					"from " + this.clazz.getSimpleName() + " e where e.from_acc = :ID1");
			query.setParameter("ID1", id);
			entities = (List<Transaction>) query.list();
			session.getTransaction().commit();
		}
		catch (Exception exception) {
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
