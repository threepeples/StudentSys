package cn.scnu.com.config;
import javax.sql.DataSource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.alibaba.druid.pool.DruidDataSource;
@Configuration
@ConfigurationProperties("spring.datasource")
@Data
public class DataSourceInitConfig {
	private String driverClassName;
	private String url;
	private String username;
	private String password;
	private Integer maxActive; //最大连接数
	private Integer maxIdle;   //最大空闲连接数
	private Integer initialSize; //初始化连接数量
	private Integer minIdle;   //最大空闲数
	//自定义创建的DruidDatasource对象返回给框架使用
	@Bean
	@Primary//当容器中存在多个同类对象时,以Primary所在的优先级最高
	public DataSource initDruidDataSource(){
		DruidDataSource datasource=new DruidDataSource();
		datasource.setDriverClassName(driverClassName);
		datasource.setUrl(url);
		datasource.setUsername(username);
		datasource.setPassword(password);
		//连接池初始化参数
		datasource.setInitialSize(initialSize);//5
		datasource.setMaxActive(maxActive);//200
		datasource.setMaxIdle(maxIdle);//8
		datasource.setMinIdle(minIdle);//3
		return datasource;
	}
}
