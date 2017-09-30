package manager;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import orm.*;
public class App {
//	  public static void main(String[] args) {
//		  //create();
//		 //retriveEmployee();
//		  updateEmployee();
//		  retriveEmployee();
//		  
//		  
//	  }
@SuppressWarnings("deprecation")
public void updateEmployee() {
	 
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = null;
    try {
        transaction = session.beginTransaction();
        String queryString = "from InternalUser where salary = :sal and employee_id = :id";
        Query query = session.createQuery(queryString);
        query.setInteger("sal", 50000);
        query.setInteger("id", 302);
        InternalUser employee = (InternalUser) query.uniqueResult();
       employee.setEmail("avenka51@asu.edu");
       //System.out.println(employee.getEmail());
        session.update(employee);
        transaction.commit();
        System.out.println(employee.getEmail());
        
        System.out.println("Employee records updated!");
    } catch (HibernateException e) {

        transaction.rollback();

        e.printStackTrace();

    } finally {

        session.close();

    }
}
public void create() {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = null;

    try {
        transaction = session.beginTransaction();
        InternalUser employee = new InternalUser();
        employee.setActive(true);
        employee.setEmail("apoorvavnktsh10@gmail.com");
        employee.setId(302);
        employee.setPhone_no("9739744178");
        employee.setPriv("Admin");
        employee.setSalary(50000);
        session.save(employee);
        transaction.commit();
        System.out.println("Records inserted sucessessfully");
    } catch (HibernateException e) {
        transaction.rollback();
        e.printStackTrace();
    } finally {
        session.close();
    }

}
public static void retriveEmployee()

{

    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = null;
    try {
        transaction = session.beginTransaction();
        List employee = session.createQuery("from InternalUser").list();

        for (Iterator iterator = employee.iterator(); iterator.hasNext();) {
            InternalUser employee1 = (InternalUser) iterator.next();
            System.out.println(employee1.getEmail() + "  "
                    + employee1.getId() + "  " + employee1.getSalary()
                    + "   " + employee1.getPriv());
        }
        transaction.commit();

    } catch (HibernateException e) {

        transaction.rollback();

        e.printStackTrace();

    } finally {

        session.close();

    }
}
}