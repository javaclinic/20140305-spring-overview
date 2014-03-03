package example01;

import java.util.Collection;

public interface EmployeeService {
	
	void saveEmployee(String id, String name, String email);
	Collection<Employee> getAllEmployees();
	int countEmployees();

}
