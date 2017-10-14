package web;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.authorize_external.AuthorizeExternalDao;
import dao.authorize_internal.AuthorizeInternalDao;
import dao.internal_users.InternalUserImpl;
import dao.transaction.TransactionDao;
import model.Authorization_External;
import model.Authorization_Internal;
import model.Internal_Users;
import model.Transactions;


@Controller
public class HomeController {
    private static final Logger logger = Logger.getLogger(HomeController.class);
    @RequestMapping({"/","/home"})
    
    public String welcome(Model model) {
        if(logger.isDebugEnabled()){
            logger.debug("Home Page requested!");
        }
        model.addAttribute("greeting", "gretting!!!");
        model.addAttribute("tagline", "Home Controller");
        
//        Internal_Users user=new Internal_Users();
//        user.setAddress("Tempe");
//        user.setEmail("ap@gmail.com");
//        user.setEmp_id(123);
//        user.setGender("f");
//        user.setName("apoorva");
//        user.setPassword("pswd");
//        user.setPriv("Tier1");
//        user.setSalary(12345);
//        user.setSsn("123456");
//        user.setThreshold(334);
//        user.setUsername("ap");
//        user.setZipcode("85281");
//        user.setContact_no("4804101771");
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("users-bean.xml");
		InternalUserImpl obj = ctx.getBean("userDao", InternalUserImpl.class);
//		obj.saveOrUpdate(user);
		
//		Internal_Users user1=obj.getbyid(123);
//		System.out.println("ID:"+user1.getName());
//		Internal_Users user2=obj.getbyemail("ap@gmail.com");
//		System.out.println("ID email:"+user2.getName());
//		Internal_Users user3=obj.getbyphone("4804101771");
//		System.out.println("ID phone:"+user3.getName());
//		Internal_Users user4=obj.getbyssn("123456");
//		System.out.println("ID ssn:"+user4.getName());
//		Internal_Users user5=obj.getbyusername("ap");
//		System.out.println("ID username:"+user5.getName());
//		List<Internal_Users> user6=obj.getbyname("apoorva");
//		System.out.println("ID name:"+user6.get(0));
//		List<Internal_Users> user7=obj.getbyrole("Tier1");
//		System.out.println("ID role:"+user7.get(0));
        Authorization_Internal authorizeInternal=new Authorization_Internal();
        authorizeInternal.setTrans_id(19823);
        authorizeInternal.setEmp_id(123);
        authorizeInternal.setAuth_state(true);
        ClassPathXmlApplicationContext ctx1 = new ClassPathXmlApplicationContext("users-bean.xml");
		AuthorizeInternalDao obj1 = ctx1.getBean("authorizeInternalDao", AuthorizeInternalDao.class);
		obj1.saveOrUpdate(authorizeInternal);
		Authorization_Internal internalAuth = obj1.getByEmpId(123);
		System.out.println(internalAuth.getEmp_id()+" " +internalAuth.getTrans_id()+" "+internalAuth.isAuth_state());
        
		Authorization_External authorizeExternal=new Authorization_External();
	    authorizeExternal.setTrans_id(19823);
	    authorizeExternal.setAcc_id("12345");
	    authorizeExternal.setAuth(true);
		AuthorizeExternalDao obj2 = ctx.getBean("authorizeExternalDao", AuthorizeExternalDao.class);
		obj2.saveOrUpdate(authorizeExternal);
		Authorization_External externalAuth = obj2.getByTransactionId(19823);
		System.out.println(externalAuth.getTrans_id()+" "+externalAuth.getAcc_id()+" " +externalAuth.isAuth());
	       
		Transactions transactions=new Transactions();
		transactions.setTrans_id(19823);
		transactions.setActive(true);
		transactions.setAmount(200);
		transactions.setEmp_id(123);
		transactions.setFrom_acc("123");
		transactions.setTo_acc("100");
		transactions.setInit_id(123);
		transactions.setType("Sv");
		TransactionDao obj3 = ctx.getBean("transactions", TransactionDao.class);
		obj3.saveOrUpdate(transactions);
		Transactions trans = obj3.getByTransactionId(19823);
		System.out.println(trans.getTrans_id()+" "+trans.getAmount()+" "+ trans.getEmp_id()+" "+trans.getFrom_acc()+" "+ trans.getTo_acc()+" " +trans.getInit_id()+" "+trans.getType()+" "+trans.isActive());
	    
		return "welcome";
    } 
}