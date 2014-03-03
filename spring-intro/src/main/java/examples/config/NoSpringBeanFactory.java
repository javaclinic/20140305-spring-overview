package examples.config;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import examples.dao.PersistenceHelper;
import examples.dao.PersistentStore;
import examples.services.AccountManager;

public class NoSpringBeanFactory {
	
	private static final Logger LOGGER = Logger.getLogger(NoSpringBeanFactory.class.getCanonicalName());

	private static Map<String,Object> beans = new HashMap<>();
	private static NoSpringBeanFactory instance = new NoSpringBeanFactory();
	
	private NoSpringBeanFactory() {
		LOGGER.info("Inside " + NoSpringBeanFactory.class.getSimpleName() + "() default constructor. Instantiating configuration factory.");
	}
	
	public static NoSpringBeanFactory buildBeans() {
		LOGGER.info("Inside " + NoSpringBeanFactory.class.getSimpleName() + ".buildBeans() initialization method. Instantiating beans.");

		AccountManager service = new AccountManager();
		PersistenceHelper helper = new PersistenceHelper();
		PersistentStore store = new PersistentStore();

		helper.setPersistentStore(store);
		service.setPersistenceHelper(helper);
		
		beans.put("accountManager", service);
		beans.put("persistenceHelper", helper);
		beans.put("persistentStore", store);
		
		return instance;
	}
	
	public Object getBean(String name) {
		LOGGER.info("Inside " + NoSpringBeanFactory.class.getSimpleName() + ".getBean() service method. Getting a bean.");
		return beans.get(name);
	}

	public <T> T getBean(String name, Class<T> clazz) {
		LOGGER.info("Inside " + NoSpringBeanFactory.class.getSimpleName() + ".getBean() service method. Getting a bean.");
		Object bean = beans.get(name);
		return (T) clazz.cast(bean);
	}

}
