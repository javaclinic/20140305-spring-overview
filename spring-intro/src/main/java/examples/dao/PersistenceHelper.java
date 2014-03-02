package examples.dao;

import java.util.List;
import java.util.logging.Logger;

import examples.model.Account;

public class PersistenceHelper {
	
	private static final Logger LOGGER = Logger.getLogger(PersistenceHelper.class.getCanonicalName());
	
	private PersistentStore database = null;
	
	public PersistenceHelper() {
		LOGGER.info("Inside " + PersistenceHelper.class.getSimpleName() + "() default constructor. Instantiating persistence helper.");
	}
	
	public void setPersistentStore (PersistentStore database) {
		LOGGER.info("Inside " + PersistenceHelper.class.getSimpleName() + ".setPersistentStore() setter method. Injecting persistent store.");
		this.database = database;
	}

	public void addAccount(Account account) {
		LOGGER.info("Inside " + PersistenceHelper.class.getSimpleName() + ".addAccount() business method. Adding an account.");
		database.store(account);
	}

	public Account getAccount(String id) {
		LOGGER.info("Inside " + PersistenceHelper.class.getSimpleName() + ".getAccount() business method. Fetching an account.");
		return database.fetchSingleAccount(id);
	}
	
	public List<Account> getAccounts() {
		LOGGER.info("Inside " + PersistenceHelper.class.getSimpleName() + ".getAccounts() business method. Fetching all accounts.");
		return database.fetchAllAccounts();
	}
	
}
