package example04;

import java.util.Collection;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example04 {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		BeanFactory factory = new AnnotationConfigApplicationContext("example04");
		
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
