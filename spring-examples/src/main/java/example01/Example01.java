package example01;

import java.util.Collection;

public class Example01 {

	public static void main(String[] args) {

		MyBeanFactory factory = new MyBeanFactory();
		factory.buildBeans();
				
		EmployeeService service = factory.getBean("service", EmployeeService.class);
		service.saveEmployee("0006", "Jeff Doe", "jeff@email.com");
		service.saveEmployee("0007", "Jean Doe", "jean@email.com");
		
		Collection<Employee> list = service.getAllEmployees();
		
		for (Employee e: list) {
			System.out.println(e);
		}
		
		System.out.println("Number of employees: " + service.countEmployees());
		
	}

}
