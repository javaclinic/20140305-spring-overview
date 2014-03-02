package examples.services;

import java.util.List;
import java.util.logging.Logger;

import examples.dao.PersistenceHelper;
import examples.model.Account;

public class AccountManager {
	
	private static final Logger LOGGER = Logger.getLogger(AccountManager.class.getCanonicalName());

	private PersistenceHelper helper = null;

	public AccountManager() {
		LOGGER.info("Inside " + AccountManager.class.getSimpleName() + "() default constructor. Instantiating account manager.");
	}
	
	public void setPersistenceHelper(PersistenceHelper helper) {
		LOGGER.info("Inside " + AccountManager.class.getSimpleName() + ".setPersistenceHelper() method. Injecting persistence helper.");
		this.helper = helper;
	}
	
	public void addAccount(String id, String name, double balance) {
		LOGGER.info("Inside " + AccountManager.class.getSimpleName() + ".addAccount() method. Adding an account.");
		Account account = new Account(id,name,balance);
		helper.addAccount(account);
	}

	public Account getAccountById(String id) {
		LOGGER.info("Inside " + AccountManager.class.getSimpleName() + ".getAccountById() method. Fetching an accounts.");
		return helper.getAccount(id);
	}

	public List<Account> getAllAccounts() {
		LOGGER.info("Inside " + AccountManager.class.getSimpleName() + ".getAllAccounts() method. Fetching all accounts.");
		return helper.getAccounts();
	}
	
	public int getAccountCount() {
		LOGGER.info("Inside " + AccountManager.class.getSimpleName() + ".getAccountCount() method. Counting all accounts.");
		return this.getAllAccounts().size();
	}
	
}
