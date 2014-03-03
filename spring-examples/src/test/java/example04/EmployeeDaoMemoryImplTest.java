package example04;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Collection;

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

	/**
	 * Test method for {@link example04.EmployeeDaoMemoryImpl#saveEmployee(example04.Employee)}.
	 */
	@Test
	public void testSaveEmployee() {
		try {
			Employee e1 = new Employee("0006", "Jeff Doe", "jeff@email.com");
			Employee e2 = new Employee("0007", "Jean Doe", "jean@email.com");
			testObject.saveEmployee(e1);
			assertEquals(6, testObject.findAllEmployees().size());
			testObject.saveEmployee(e2);
			assertEquals(7, testObject.findAllEmployees().size());
		} catch (RuntimeException re) {
			fail("Should not catch any runtime exceptions: " + re.getMessage());
		}
	}

	/**
	 * Test method for {@link example04.EmployeeDaoMemoryImpl#deleteEmployee(example04.Employee)}.
	 */
	@Test
	public void testDeleteEmployee() {
		try {
			testObject.deleteEmployee(new Employee());
		} catch (RuntimeException re) {
			assertEquals("Not implemented.", re.getMessage());
		}
	}

	/**
	 * Test method for {@link example04.EmployeeDaoMemoryImpl#updateEmployee(example04.Employee)}.
	 */
	@Test
	public void testUpdateEmployee() {
		try {
			testObject.updateEmployee(new Employee());
		} catch (RuntimeException re) {
			assertEquals("Not implemented.", re.getMessage());
		}
	}

	/**
	 * Test method for {@link example04.EmployeeDaoMemoryImpl#findEmployeeById(java.lang.String)}.
	 */
	@Test
	public void testFindEmployeeById() {
		try {
			testObject.findEmployeeById("0001");
		} catch (RuntimeException re) {
			assertEquals("Not implemented.", re.getMessage());
		}
	}

	/**
	 * Test method for {@link example04.EmployeeDaoMemoryImpl#findEmployeesByName(java.lang.String)}.
	 */
	@Test
	public void testFindEmployeesByName() {
		try {
			testObject.findEmployeesByName("J*");
		} catch (RuntimeException re) {
			assertEquals("Not implemented.", re.getMessage());
		}
	}

	/**
	 * Test method for {@link example04.EmployeeDaoMemoryImpl#findAllEmployees()}.
	 */
	@Test
	public void testFindAllEmployees() {
		Collection<Employee> result = testObject.findAllEmployees();
		assertEquals(5, result.size());
	}

}
