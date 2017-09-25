package manager;
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
	        System.out.println(person.getEmail());
	                
	        session.close();
	    }

}
