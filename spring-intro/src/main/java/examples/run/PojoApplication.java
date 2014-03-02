package examples.run;

import examples.config.NoSpringFactory;
import examples.services.AccountManager;

public class PojoApplication {

	public static void main(String[] args) {
		
		NoSpringFactory factory = NoSpringFactory.buildBeans();
		
		AccountManager service = factory.getBean("accountManager", AccountManager.class);
		
		service.addAccount("0000-0001", "John Doe", 1000.0);
		service.addAccount("0000-0002", "John Doe", 2000.0);
		service.addAccount("0000-0003", "Jane Doe", 3000.0);
		service.addAccount("0000-0004", "Jill Doe", 4000.0);
		
		
		
	}

}
