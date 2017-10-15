package web;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.accounts.AccountsImpl;
import dao.authorize_external.AuthorizeExternalDao;
import dao.authorize_internal.AuthorizeInternalDao;
import dao.cards.CardResult;
import dao.cards.CardsImpl;
import dao.external_users.ExternalUserImpl;
import dao.internal_users.InternalUserImpl;
import dao.transaction.TransactionDao;
import model.Accounts;
import model.Authorization_External;
import model.Authorization_Internal;
import model.Cards;
import model.External_Users;
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

//		INTERNAL USERS IMPLEMENTATION AND CALLING.        
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
//		//obj.saveOrUpdate(user);
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

        
//        External_Users user=new External_Users();
//        user.setAddress("Tempe");
//        user.setEmail("ap@gmail.com");
//        user.setUser_id(123);
//        user.setGender("f");
//        user.setName("apoorva");
//        user.setPassword("pswd");
//        user.setRole("Tier1");
//        user.setSsn("123456");
//        user.setThreshold(334);
//        user.setUser_name("ap");
//        user.setZipcode("85281");
//        user.setContact_no("4804101771");
//        ClassPathXmlApplicationContext ctx1 = new ClassPathXmlApplicationContext("users-bean.xml");
//		ExternalUserImpl obj1 = ctx.getBean("externaluserDao", ExternalUserImpl.class);
//		obj1.save(user);
//		External_Users user1=obj1.getbyid(123);
//		System.out.println("ID:"+user1.getName());
//		External_Users user2=obj1.getbyemail("ap@gmail.com");
//		System.out.println("ID email:"+user2.getName());
//		External_Users user3=obj1.getbyphone("4804101771");
//		System.out.println("ID phone:"+user3.getName());
//		External_Users user4=obj1.getbyssn("123456");
//		System.out.println("ID ssn:"+user4.getName());
//		External_Users user5=obj1.getbyusername("ap");
//		System.out.println("ID username:"+user5.getName());
//		List<External_Users> user6=obj1.getbyname("apoorva");
//		System.out.println("ID name:"+user6.get(0));
//		List<External_Users> user7=obj1.getbyrole("Tier1");
//		System.out.println("ID role:"+user7.get(0));

//		Accounts acc=new Accounts();
//		acc.setAcc_id("3457234567890");
//		acc.setType("Checking");
//		acc.setBalance(1234567);
//		acc.setUser_id(123);
//      ClassPathXmlApplicationContext ctx3 = new ClassPathXmlApplicationContext("users-bean.xml");
//		AccountsImpl obj3 = ctx3.getBean("accountsDao", AccountsImpl.class);
//		//obj3.save(acc);
//		obj3.getbyaccid("3457234567890");
//		obj3.getbytype("Checking");	
//		obj3.getbyuserid(123);
//        Authorization_Internal authorizeInternal=new Authorization_Internal();
//        authorizeInternal.setTrans_id(19823);
//        authorizeInternal.setEmp_id(123);
//        authorizeInternal.setAuth_state(true);
//        ClassPathXmlApplicationContext ctx1 = new ClassPathXmlApplicationContext("users-bean.xml");
//		AuthorizeInternalDao obj1 = ctx1.getBean("authorizeInternalDao", AuthorizeInternalDao.class);
//		obj1.saveOrUpdate(authorizeInternal);
//		Authorization_Internal internalAuth = obj1.getByEmpId(123);
//		System.out.println(internalAuth.getEmp_id()+" " +internalAuth.getTrans_id()+" "+internalAuth.isAuth_state());
//        
//		Authorization_External authorizeExternal=new Authorization_External();
//	    authorizeExternal.setTrans_id(19823);
//	    authorizeExternal.setAcc_id("12345");
//	    authorizeExternal.setAuth(true);
//		AuthorizeExternalDao obj2 = ctx.getBean("authorizeExternalDao", AuthorizeExternalDao.class);
//		obj2.saveOrUpdate(authorizeExternal);
//		Authorization_External externalAuth = obj2.getByTransactionId(19823);
//		System.out.println(externalAuth.getTrans_id()+" "+externalAuth.getAcc_id()+" " +externalAuth.isAuth());
//	       
//		Transactions transactions=new Transactions();
//		transactions.setTrans_id(19823);
//		transactions.setActive(true);
//		transactions.setAmount(200);
//		transactions.setEmp_id(123);
//		transactions.setFrom_acc("123");
//		transactions.setTo_acc("100");
//		transactions.setInit_id(123);
//		transactions.setType("Sv");
//		TransactionDao obj3 = ctx.getBean("transactions", TransactionDao.class);
//		obj3.saveOrUpdate(transactions);
//		Transactions trans = obj3.getByTransactionId(19823);
//		System.out.println(trans.getTrans_id()+" "+trans.getAmount()+" "+ trans.getEmp_id()+" "+trans.getFrom_acc()+" "+ trans.getTo_acc()+" " +trans.getInit_id()+" "+trans.getType()+" "+trans.isActive());
//	    
//		Cards card=new Cards();
//		card.setAcc_id("3457234567890");
//		card.setActive(true);
//		card.setCard_no("123456789012345");
//		card.setCvv(333);
//		card.setType("Debit");
//	      ClassPathXmlApplicationContext ctx4 = new ClassPathXmlApplicationContext("users-bean.xml");
//			CardsImpl obj4 = ctx4.getBean("cardsDao", CardsImpl.class);
//			//obj4.save(card);
//			obj4.getbyaccid("3457234567890");
//			obj4.getbycardno("123456789012345");
//			obj4.getbytype("Debit");
//			CardResult c=obj4.getcardbyacc_id("3457234567890");
//			System.out.println(c.getCard_no()+" "+c.getType()+" "+c.getBalance()+" "+c.getAcc_type());
        return "welcome";
    } 
}