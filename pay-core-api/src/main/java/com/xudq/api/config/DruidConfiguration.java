package com.xudq.api.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */
@Configuration
public class DruidConfiguration {
	@Bean
	public ServletRegistrationBean druidServlet() {
		return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
	}

	@Bean(name = "druidDataSource")
	public DataSource druidDataSource(@Value("${spring.datasource.driverClassName}") String driver,
	                                  @Value("${spring.datasource.url}") String url,
	                                  @Value("${spring.datasource.username}") String username,
	                                  @Value("${spring.datasource.password}") String password) {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(driver);
		druidDataSource.setUrl(url);
		druidDataSource.setUsername(username);
		druidDataSource.setPassword(password);

		druidDataSource.setPoolPreparedStatements(true);
		druidDataSource.setMaxWait(60000);
		druidDataSource.setMaxActive(20);
		druidDataSource.setInitialSize(10);
		druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
		druidDataSource.setMinIdle(10);
		druidDataSource.setMinEvictableIdleTimeMillis(3000000);
		druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
		druidDataSource.setValidationQuery("SELECT 1");
		druidDataSource.setTestWhileIdle(true);
		druidDataSource.setTestOnReturn(false);
		druidDataSource.setTestOnBorrow(false);

		WallFilter wall = new WallFilter();
		WallConfig wallConfig = new WallConfig();
		wallConfig.setMultiStatementAllow(true);
		wall.setConfig(wallConfig);
		List<Filter> filters = Lists.newArrayList();
		filters.add(wall);
		druidDataSource.setProxyFilters(filters);
		try {
			druidDataSource.setFilters("stat, wall");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return druidDataSource;
	}


}
