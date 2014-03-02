package examples.run;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import examples.services.AccountManager;

public class PojoApplicationWithSpring {

	public static void main(String[] args) {
		
		ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		AccountManager service = factory.getBean("accountManager", AccountManager.class);
		
		service.addAccount("0000-0001", "John Doe", 1000.0);
		service.addAccount("0000-0002", "John Doe", 2000.0);
		service.addAccount("0000-0003", "Jane Doe", 3000.0);
		service.addAccount("0000-0004", "Jill Doe", 4000.0);
		
		System.out.println("Number of accounts: " + service.getAccountCount());
			
	}

}
