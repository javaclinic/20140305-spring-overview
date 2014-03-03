package example04;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class MyConfiguration {

	// other beans will automatically be scanned and added to factory
	
	@Bean(name="datasource")
	public DataSource getEmbeddedDatasource() {
		 EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		 EmbeddedDatabase datasource = builder.setType(EmbeddedDatabaseType.HSQL).addScript("schema.sql").addScript("data.sql").build();
		 return datasource;
	}
	
	@Bean(name="oldDatasource")
	public DataSource getJdbcDatasource() {
		org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
		ds.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
		ds.setUrl("jdbc:hsqldb:mem:mydb");
		ds.setUsername("sa");
		ds.setPassword("");
		return ds;
	}
	
}
