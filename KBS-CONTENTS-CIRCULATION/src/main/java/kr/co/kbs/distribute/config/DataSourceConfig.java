package kr.co.kbs.distribute.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {
	
	@Autowired
	private Environment env;

	@Bean(destroyMethod = "")
	public DataSource dataSource() {
		BasicDataSource datasource = new BasicDataSource();
		datasource.setDriverClassName(env.getProperty("jdbc.driver"));
		datasource.setUrl(env.getProperty("jdbc.url"));
		//datasource.setUrl("jdbc:mysql://127.0.0.1:3306/MD_Project");
		datasource.setUsername(env.getProperty("jdbc.user"));
		datasource.setPassword(env.getProperty("jdbc.pw"));
		datasource.setValidationQuery("SELECT 1 ");
		datasource.setTestWhileIdle(true);
		datasource.setTimeBetweenEvictionRunsMillis(60000);
		datasource.setInitialSize(30);
		datasource.setMaxTotal(30);
		datasource.setMaxIdle(30);
		datasource.setMaxWaitMillis(10000);
		datasource.setRemoveAbandonedTimeout(5);
		datasource.setRemoveAbandonedOnBorrow(true);
		datasource.setRemoveAbandonedOnMaintenance(true);
		datasource.setDefaultAutoCommit(true);
		return datasource;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource());
		transactionManager.setGlobalRollbackOnParticipationFailure(false);
		return transactionManager;
	}
	
}