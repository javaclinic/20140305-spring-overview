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

		EmployeeDaoJdbcImpl dao = new EmployeeDaoJdbcImpl();
		datasource = buildDatasource();
		dao.setDatasource(datasource);
		
		this.initializeSchema("schema.sql");
		this.initializeData("data.sql");
		
		beans.put("dao", dao);
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
	
	private DataSource buildDatasource() {
		org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
		ds.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
		ds.setUrl("jdbc:hsqldb:mem:mydb");
		ds.setUsername("sa");
		ds.setPassword("");
		return ds;
	}
	
	private void initializeSchema(String filename) {
		
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
	
	private void initializeData(String filename) {
		
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
