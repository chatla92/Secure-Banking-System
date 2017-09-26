package manager;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import orm.*;
public class App {
	  public static void main(String[] args) {
	        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	        Session session = sessionFactory.openSession();
	        session.beginTransaction();
	        InternalUser p1 = new InternalUser();
	       p1.setActive(true);
	       p1.setEmail("apoorvavnktsh10@gmail.com");
	       p1.setId(123);
	       p1.setPhone_no("4804101771");
	       p1.setPriv("Tier1");
	       p1.setSalary(150000);
	       System.out.println(p1.getEmail());
	        int personId = (int) session.save(p1);
	        session.getTransaction().commit();
	        
	        InternalUser person = (InternalUser) session.get(InternalUser.class, personId);        
	        InternalUser person1=getPersonById(123);
	        System.out.println(person1.getEmail());
	        System.out.println(person1.getPhone_no());
	        System.out.println(person1.getPriv());
	        System.out.println(person1.getSalary());
	        session.close();
	    }
	  static InternalUser getPersonById(int id)
	  {
		  Session session = null;
	        InternalUser user = null;
	        try {
	            session = HibernateUtil.getSessionFactory().openSession();
	            user =  session.load(InternalUser.class,
	                    id);
	            Hibernate.initialize(user);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (session != null && session.isOpen()) {
	                session.close();
	            }
	        }
	        return user;
	  }

}
