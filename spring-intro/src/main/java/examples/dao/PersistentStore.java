package examples.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import examples.model.Account;

public class PersistentStore {
	
	private static final Logger LOGGER = Logger.getLogger(PersistentStore.class.getCanonicalName());
	
	private final Map<String,Account> database = new HashMap<>();
	
	public PersistentStore() {
		LOGGER.info("Inside " + PersistentStore.class.getSimpleName() + "() default constructor. Instantiating persistent store.");
	}
	
	public void store(Account account) {
		LOGGER.info("Inside " + PersistentStore.class.getSimpleName() + ".store() method. Adding an account.");
		String id = account.getId();
		if ( id == null ) throw new RuntimeException("Account id cannot be null.");
		database.put(id,account);
	}
	
	public Account fetchSingleAccount(String id) {
		LOGGER.info("Inside " + PersistentStore.class.getSimpleName() + ".fetchSingleAccount() method. Fetching an account.");
		return database.get(id);
	}
	
	public List<Account> fetchAllAccounts() {
		LOGGER.info("Inside " + PersistentStore.class.getSimpleName() + ".fetchAllAccounts() method. Fetching all accounts.");
		List<Account> result = new ArrayList<>();
		for (Account account : database.values()) {
			result.add(account);
		}
		return result;
	}

}
