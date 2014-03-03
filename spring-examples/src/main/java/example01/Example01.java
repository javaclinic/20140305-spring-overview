package example01;

import java.util.Collection;

public class Example01 {

	public static void main(String[] args) {

		MyBeanFactory factory = new MyBeanFactory();
		factory.buildBeans();
		
		Employee e1 = new Employee("0006", "Jeff Doe", "jeff@email.com");
		Employee e2 = new Employee("0007", "Jean Doe", "jean@email.com");
		
		EmployeeDao dao = factory.getBean("dao", EmployeeDao.class);
		dao.saveEmployee(e1);
		dao.saveEmployee(e2);
		
		Collection<Employee> list = dao.findAllEmployees();
		
		for (Employee e: list) {
			System.out.println(e);
		}
		
		System.out.println("Number of employees: " + list.size());
		
	}

}
