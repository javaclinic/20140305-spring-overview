package example01;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;

public class MyBeanFactory {
	
	private static final Logger LOGGER = Logger.getLogger(MyBeanFactory.class.getCanonicalName());

	private Map<String,Object> beans = new HashMap<>();
	private DataSource datasource = null;
	
	public MyBeanFactory() {
		LOGGER.info("Inside " + MyBeanFactory.class.getSimpleName() + "() default constructor. Instantiating configuration factory.");
	}
	
	public void buildBeans() {
		LOGGER.info("Inside " + MyBeanFactory.class.getSimpleName() + ".buildBeans() initialization method. Instantiating beans.");

		// JDBC implemntation of EmployeeDao
		EmployeeDaoJdbcImpl dao = new EmployeeDaoJdbcImpl();
		datasource = buildDatasource();
		this.initializeSchema("schema.sql");
		this.initializeData("data.sql");
		dao.setDatasource(datasource);
		
		// In-memory implementation of EmployeeDao (not used)
		EmployeeDaoMemoryImpl dao2 = new EmployeeDaoMemoryImpl();
		
		// register all beans with the MyBeanFactory
		beans.put("dao", dao);
		beans.put("dao2", dao2);
		beans.put("datasource", datasource);
	
	}
	
	public Object getBean(String name) {
		LOGGER.info("Inside " + MyBeanFactory.class.getSimpleName() + ".getBean() service method. Getting a bean.");
		return beans.get(name);
	}

	public <T> T getBean(String name, Class<T> clazz) {
		LOGGER.info("Inside " + MyBeanFactory.class.getSimpleName() + ".getBean() service method. Getting a bean.");
		Object bean = beans.get(name);
		return (T) clazz.cast(bean);
	}
	
	
	/*
	 * Helper method to initialize datasource pool. Using Tomcat JDBC Pool and HSQLDB in-memory database.
	 */
	private DataSource buildDatasource() {
		LOGGER.info("Inside " + MyBeanFactory.class.getSimpleName() + ".buildDatasource() helper method. Instantiating datasource (connection pool).");
		org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
		ds.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
		ds.setUrl("jdbc:hsqldb:mem:mydb");
		ds.setUsername("sa");
		ds.setPassword("");
		return ds;
	}

	/*
	 * Helper method to initialize schema. Using HSQLDB SqlFile to run SQL files.
	 */
	private void initializeSchema(String filename) {

		LOGGER.info("Inside " + MyBeanFactory.class.getSimpleName() + ".initializeSchema() helper method. Initializing schema from a file.");

		Connection c = null;
		
		try {
			URL url = this.getClass().getClassLoader().getResource(filename);
			File file = new File(url.toURI());
			SqlFile sqlfile = new SqlFile(file);
			c = datasource.getConnection();
			sqlfile.setConnection(c);
			sqlfile.execute();			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (SqlToolError ste) {
			ste.printStackTrace();
		} catch (URISyntaxException use) {
			use.printStackTrace();
		} finally {
			if ( c!= null ) try { c.close(); } catch (SQLException ex) { ex.printStackTrace(); }
		}
		
	}
	
	/*
	 * Helper method to initialize data. Using HSQLDB SqlFile to run SQL files.
	 */
	private void initializeData(String filename) {
		
		LOGGER.info("Inside " + MyBeanFactory.class.getSimpleName() + ".initializeData() helper method. Initializing seed data from a file.");

		Connection c = null;
		
		try {
			URL url = this.getClass().getClassLoader().getResource(filename);
			File file = new File(url.toURI());
			SqlFile sqlfile = new SqlFile(file);
			c = datasource.getConnection();
			sqlfile.setConnection(c);
			sqlfile.execute();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (SqlToolError ste) {
			ste.printStackTrace();
		} catch (URISyntaxException use) {
			use.printStackTrace();
		} finally {
			if ( c!= null ) try { c.close(); } catch (SQLException ex) { ex.printStackTrace(); }
		}
		
	}
	
}
