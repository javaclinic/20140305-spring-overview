package example02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DataSource.class)
public class EmployeeDaoJdbcImplTest {
		
	private static List<Employee> testData;
	
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
		DataSource datasource = Mockito.mock(DataSource.class);
		Connection connection = Mockito.mock(Connection.class);
		PreparedStatement ps = Mockito.mock(PreparedStatement.class);
		String sql = "INSERT INTO employees (id,name,email) VALUES (?,?,?)";
		
		Employee employee = new Employee("0006", "Jeff Doe", "jeff@email.com");
		
		EmployeeDaoJdbcImpl testObject = new EmployeeDaoJdbcImpl();
		testObject.setDatasource(datasource);
		
		try {
			
			Mockito.doReturn(connection).when(datasource).getConnection();
			Mockito.doReturn(ps).when(connection).prepareStatement(sql);
			Mockito.doNothing().when(ps).setString(1, "0006");
			Mockito.doNothing().when(ps).setString(2, "Jeff Doe");
			Mockito.doNothing().when(ps).setString(3, "jeff@email.com");
			Mockito.doReturn(new Integer(1)).when(ps).executeUpdate();
			Mockito.doNothing().when(ps).close();
			Mockito.doNothing().when(connection).close();
		
			testObject.saveEmployee(employee);
		
			Mockito.verify(datasource).getConnection();
			Mockito.verify(connection).prepareStatement(sql);
			Mockito.verify(ps).setString(1,"0006");
			Mockito.verify(ps).setString(2,"Jeff Doe");
			Mockito.verify(ps).setString(3,"jeff@email.com");
			Mockito.verify(ps).executeUpdate();
			
			Mockito.verify(ps).close();
			Mockito.verify(connection).close();
			
		} catch (SQLException sqle) {
			fail("Should not throw the exception." + sqle.getMessage());
		}
		Mockito.verifyNoMoreInteractions(ps);
		Mockito.verifyNoMoreInteractions(connection);
		Mockito.verifyNoMoreInteractions(datasource);
		
	}
	
	@Test
	public void testFindAllEmployees() {
		
		DataSource datasource = Mockito.mock(DataSource.class);
		Connection connection = Mockito.mock(Connection.class);
		PreparedStatement ps = Mockito.mock(PreparedStatement.class);
		ResultSet rs = Mockito.mock(ResultSet.class);

		String sql = "SELECT id,name,email FROM employees";

		EmployeeDaoJdbcImpl testObject = new EmployeeDaoJdbcImpl();
		testObject.setDatasource(datasource);
		
		try {
			
			Mockito.doReturn(connection).when(datasource).getConnection();
			Mockito.doReturn(ps).when(connection).prepareStatement(sql);
			Mockito.doReturn(rs).when(ps).executeQuery();
			
			Mockito.when(rs.next()).thenReturn(true, true, true, true, true, false);
			Mockito.when(rs.getString("id")).thenReturn("0001", "0002", "0003", "0004", "0005");
			Mockito.when(rs.getString("name")).thenReturn("John Doe", "Jane Doe", "Jack Doe", "Jill Doe", "Jenn Doe");
			Mockito.when(rs.getString("email")).thenReturn("john@email.com", "jane@email.com", "jack@email.com", "jill@email.com", "jenn@email.com");
			
			Collection<Employee> result = testObject.findAllEmployees();
			assertEquals(5, result.size());
			
			Mockito.verify(datasource).getConnection();
			Mockito.verify(connection).prepareStatement(sql);
			Mockito.verify(ps).executeQuery();
			Mockito.verify(rs, Mockito.times(6)).next();
			Mockito.verify(rs, Mockito.times(5)).getString("id");
			Mockito.verify(rs, Mockito.times(5)).getString("name");
			Mockito.verify(rs, Mockito.times(5)).getString("email");
			Mockito.verify(rs).close();
			Mockito.verify(ps).close();
			Mockito.verify(connection).close();
			
		} catch (SQLException sqle) {
			fail("Should not throw the exception." + sqle.getMessage());
		}
		
		Mockito.verifyNoMoreInteractions(rs);
		Mockito.verifyNoMoreInteractions(ps);
		Mockito.verifyNoMoreInteractions(connection);
		Mockito.verifyNoMoreInteractions(datasource);
		
	}
	
	@Test
	public void testFindEmployeeByName() {
		try {
			EmployeeDaoJdbcImpl testObject = new EmployeeDaoJdbcImpl();
			testObject.findEmployeesByName("J*");
			fail("Should have thrown a runtime exception.");
		} catch (RuntimeException re) {
			assertEquals("Not implemented.", re.getMessage());
		}
	}
	
	@Test
	public void testDeleteEmployee() {
		try {
			EmployeeDaoJdbcImpl testObject = new EmployeeDaoJdbcImpl();
			testObject.deleteEmployee(new Employee());
			fail("Should have thrown a runtime exception.");
		} catch (RuntimeException re) {
			assertEquals("Not implemented.", re.getMessage());
		}
	}

	@Test
	public void testUpdateEmployee() {
		try {
			EmployeeDaoJdbcImpl testObject = new EmployeeDaoJdbcImpl();
			testObject.updateEmployee(new Employee());
			fail("Should have thrown a runtime exception.");
		} catch (RuntimeException re) {
			assertEquals("Not implemented.", re.getMessage());
		}
	}

	@Test
	public void testFindEmployeeById() {
		try {
			EmployeeDaoJdbcImpl testObject = new EmployeeDaoJdbcImpl();
			testObject.findEmployeeById("0001");
			fail("Should have thrown a runtime exception.");
		} catch (RuntimeException re) {
			assertEquals("Not implemented.", re.getMessage());
		}
	}
	
}
