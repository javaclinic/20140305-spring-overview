package example01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class EmployeeDaoJdbcImpl implements EmployeeDao {
	
	private static final Logger LOGGER = Logger.getLogger(EmployeeDaoJdbcImpl.class.getCanonicalName());

	private DataSource datasource;
	
	public EmployeeDaoJdbcImpl() {
		LOGGER.info("Inside " + EmployeeDaoJdbcImpl.class.getSimpleName() + "() default constructor. Instantiating dao.");
	}
	
	public void setDatasource(DataSource datasource) {
		LOGGER.info("Inside " + EmployeeDaoJdbcImpl.class.getSimpleName() + ".setDatasource() setter method. Injecting datasource.");
		this.datasource = datasource;
	}

	@Override
	public void saveEmployee(Employee e) {
		LOGGER.info("Inside " + EmployeeDaoJdbcImpl.class.getSimpleName() + ".saveEmployee() business method. Saving employee.");
		Connection c = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO employees (id,name,email) VALUES (?,?,?)";
		
		try {
			
			c = datasource.getConnection();
			ps = c.prepareStatement(sql);
			ps.setString(1, e.getId());
			ps.setString(2, e.getName());
			ps.setString(3, e.getEmail());
			int updated = ps.executeUpdate();
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if ( ps != null ) try { ps.close(); } catch (SQLException ex) { ex.printStackTrace(); }
			if ( c != null ) try { c.close(); } catch (SQLException ex) { ex.printStackTrace(); }
		}
		
	}

	@Override
	public void deleteEmployee(Employee e) {
		throw new RuntimeException("Not implemented.");
	}

	@Override
	public void updateEmployee(Employee e) {
		throw new RuntimeException("Not implemented.");
	}

	@Override
	public Employee findEmployeeById(String id) {
		throw new RuntimeException("Not implemented.");
	}
	
	@Override
	public Collection<Employee> findEmployeesByName(String query) {
		throw new RuntimeException("Not implemented.");
	}

	@Override
	public Collection<Employee> findAllEmployees() {

		LOGGER.info("Inside " + EmployeeDaoJdbcImpl.class.getSimpleName() + ".findAllEmployees() business method. Fetching all employees.");

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT id,name,email FROM employees";
		Collection<Employee> result = new ArrayList<>();
		
		try {
			
			c = datasource.getConnection();
			ps = c.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setId(rs.getString("id"));
				employee.setName(rs.getString("name"));
				employee.setEmail(rs.getString("email"));
				result.add(employee);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if ( rs != null ) try { rs.close(); } catch (SQLException ex) { ex.printStackTrace(); }
			if ( ps != null ) try { ps.close(); } catch (SQLException ex) { ex.printStackTrace(); }
			if ( c != null ) try { c.close(); } catch (SQLException ex) { ex.printStackTrace(); }
		}
		return result;		
	}

}
