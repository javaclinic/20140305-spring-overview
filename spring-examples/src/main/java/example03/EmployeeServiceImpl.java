package example03;

import java.util.Collection;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("service")
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger LOGGER = Logger.getLogger(EmployeeServiceImpl.class.getCanonicalName());

	private EmployeeDao dao;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeDao dao) {
		LOGGER.info("Inside " + EmployeeServiceImpl.class.getSimpleName() + "() constructor. Instantiating service (injecting dao).");
		this.dao = dao;
	}
	
	@Override
	public void saveEmployee(String id, String name, String email) {
		LOGGER.info("Inside " + EmployeeServiceImpl.class.getSimpleName() + ".saveEmployee() business method. Saving employee.");
		Employee e = new Employee(id,name,email);
		dao.saveEmployee(e);
	}

	@Override
	public Collection<Employee> getAllEmployees() {
		LOGGER.info("Inside " + EmployeeDaoJdbcImpl.class.getSimpleName() + ".getAllEmployees() business method. Fetching all employees.");
		return dao.findAllEmployees();
	}

	@Override
	public int countEmployees() {
		LOGGER.info("Inside " + EmployeeDaoJdbcImpl.class.getSimpleName() + ".countEmployees() business method. Counting all employees.");
		return dao.findAllEmployees().size();
	}

}
