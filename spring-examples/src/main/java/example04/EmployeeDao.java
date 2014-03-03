package example04;

import java.util.Collection;

public interface EmployeeDao {

	// CRUD methods
	void saveEmployee(Employee e);
	void deleteEmployee(Employee e);
	void updateEmployee(Employee e);
	
	// Finder methods
	Employee findEmployeeById(String id);
	Collection<Employee> findEmployeesByName(String query);
	Collection<Employee> findAllEmployees();
	
}
