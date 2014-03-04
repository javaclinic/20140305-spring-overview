package example03;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(EmployeeDaoJdbcImpl.class)
public class EmployeeServiceImplTest {
		
	private static Collection<Employee> testData;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testData = new ArrayList<>();
		testData.add(new Employee("0001", "John Doe", "john@email.com"));
		testData.add(new Employee("0002", "Jane Doe", "jane@email.com"));
		testData.add(new Employee("0003", "Jack Doe", "jack@email.com"));
		testData.add(new Employee("0004", "Jill Doe", "jill@email.com"));
		testData.add(new Employee("0005", "Jenn Doe", "jenn@email.com"));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		testData = null;
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveEmployee() {
		EmployeeDaoJdbcImpl dao = PowerMockito.mock(EmployeeDaoJdbcImpl.class);
		EmployeeService testObject = new EmployeeServiceImpl(dao);
		PowerMockito.doNothing().when(dao).saveEmployee(Mockito.any(Employee.class));
		testObject.saveEmployee("0006", "Jeff Doe", "jeff@email.com");
		Mockito.verify(dao).saveEmployee(Mockito.any(Employee.class));
		Mockito.verifyNoMoreInteractions(dao);
	}

	@Test
	public void testGetAllEmployees() {
		EmployeeDao dao = PowerMockito.mock(EmployeeDaoJdbcImpl.class);
		EmployeeService testObject = new EmployeeServiceImpl(dao);
		PowerMockito.doReturn(testData).when(dao).findAllEmployees();
		Collection<Employee> result = testObject.getAllEmployees();
		Assert.assertEquals(5, result.size());
		Mockito.verify(dao).findAllEmployees();
		Mockito.verifyNoMoreInteractions(dao);

	}
	
	@Test
	public void testCountEmployees() {
		EmployeeDao dao = PowerMockito.mock(EmployeeDaoJdbcImpl.class);
		EmployeeService testObject = new EmployeeServiceImpl(dao);
		PowerMockito.doReturn(testData).when(dao).findAllEmployees();
		int result = testObject.countEmployees();
		Assert.assertEquals(5, result);
		Mockito.verify(dao).findAllEmployees();
		Mockito.verifyNoMoreInteractions(dao);
	}

}
