package hibernateDao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import hibernateModel.Accounts;
import hibernateModel.ExternalAuthorization;

public class ExternalAuthorizationDao extends AbstractDao<ExternalAuthorization> {

	public ExternalAuthorizationDao() {
		super(ExternalAuthorization.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<ExternalAuthorization> getByAccountId(Accounts id) {
		List<ExternalAuthorization> entities = null;
		Session session = null;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from " + this.clazz.getSimpleName() + " e where e.account = :ID");
			query.setParameter("ID", id);
			entities = (List<ExternalAuthorization>) query.list();
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
