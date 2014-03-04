package example03;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EmployeeDaoMemoryImplTest {

	private EmployeeDao testObject;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		testObject = new EmployeeDaoMemoryImpl();
	}

	@After
	public void tearDown() throws Exception {
		testObject = null;
	}

	@Test
	public void testSaveEmployee() {
		Employee e1 = new Employee("0006", "Jeff Doe", "jeff@email.com");
		Employee e2 = new Employee("0007", "Jean Doe", "jean@email.com");
		testObject.saveEmployee(e1);
		assertEquals(6, testObject.findAllEmployees().size());
		testObject.saveEmployee(e2);
		assertEquals(7, testObject.findAllEmployees().size());
	}

	@Test
	public void testDeleteEmployee() {
		testObject.deleteEmployee(new Employee());
		Employee e1 = new Employee("0006", "Jeff Doe", "jeff@email.com");
		testObject.saveEmployee(e1);
		assertEquals(6, testObject.findAllEmployees().size());
		testObject.deleteEmployee(e1);
		assertEquals(5, testObject.findAllEmployees().size());
	}

	@Test
	public void testUpdateEmployee() {
		Employee e1 = testObject.findEmployeeById("0001");
		e1.setName("John Doe Jr.");
		e1.setEmail("john.jr@email.com");
		testObject.updateEmployee(e1);
		Employee e2 = testObject.findEmployeeById("0001");
		assertEquals("0001", e2.getId());
		assertEquals("John Doe Jr.", e2.getName());
		assertEquals("john.jr@email.com", e2.getEmail());
	}

	@Test
	public void testFindEmployeeById() {	
		Employee e1 = testObject.findEmployeeById("0001");
		assertNotNull(e1);
		assertEquals("0001", e1.getId());
		assertEquals("John Doe", e1.getName());
		assertEquals("john@email.com", e1.getEmail());
	}

	@Test
	public void testFindEmployeesByName() {
		Collection<Employee> result = testObject.findEmployeesByName("Ja.*");
		assertEquals(2,result.size());
		for (Employee e : result) {
			assertEquals(true, Pattern.matches("Ja.*", e.getName()));
		}
	}

	@Test
	public void testFindAllEmployees() {
		Collection<Employee> result = testObject.findAllEmployees();
		assertEquals(5, result.size());
	}

}
