package example03;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.stereotype.Repository;

@Repository("dao2")
public class EmployeeDaoMemoryImpl implements EmployeeDao {
	
	private static final Logger LOGGER = Logger.getLogger(EmployeeDaoMemoryImpl.class.getCanonicalName());
	private Map<String,Employee> database = new HashMap<>();
		
	public EmployeeDaoMemoryImpl() {
		LOGGER.info("Inside " + EmployeeDaoMemoryImpl.class.getSimpleName() + "() default constructor. Instantiating dao.");
		database.put("0001", new Employee("0001", "John Doe", "john@email.com"));
		database.put("0002", new Employee("0002", "Jane Doe", "jane@email.com"));
		database.put("0003", new Employee("0003", "Jack Doe", "jack@email.com"));
		database.put("0004", new Employee("0004", "Jill Doe", "jill@email.com"));
		database.put("0005", new Employee("0005", "Jenn Doe", "jenn@email.com"));
	}
	
	public void saveEmployee(Employee e) {
		LOGGER.info("Inside " + EmployeeDaoMemoryImpl.class.getSimpleName() + ".saveEmployee() business method. Saving employee.");
		database.put(e.getId(), e);
	}

	public void deleteEmployee(Employee e) {
		throw new RuntimeException("Not implemented.");
	}

	public void updateEmployee(Employee e) {
		throw new RuntimeException("Not implemented.");
	}

	public Employee findEmployeeById(String id) {
		throw new RuntimeException("Not implemented.");
	}

	public Collection<Employee> findEmployeesByName(String query) {
		throw new RuntimeException("Not implemented.");
	}

	public Collection<Employee> findAllEmployees() {
		LOGGER.info("Inside " + EmployeeDaoMemoryImpl.class.getSimpleName() + ".findAllEmployees() business method. Fetching all employees.");
		List<Employee> result = new ArrayList<>();
		for (Employee e : database.values()) {
			result.add(e);
		}
		return result;
	}

}
