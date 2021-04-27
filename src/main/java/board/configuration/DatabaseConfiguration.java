package board.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
// 설정파일의 위치를 설정하는 어노테이션
// @ProperetySource 어노테이션을 여러개 사용 시 설정파일을 여러개 지정할 수 있음
@PropertySource("classpath:/application.properties")
public class DatabaseConfiguration {

	@Autowired
	private ApplicationContext applicationContext;
	
//	히키라 CP를 사용하여 데이터 베이스와 연결하기 위한 부분
	@Bean
//	@ConfigurationProperties 어노테이션의 prefix가 지정하고 있는 datasource를 사용한다는 의미
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	
	@Bean
	public DataSource dataSource() throws Exception {
//		앞에서 만든 히카리CP 설정을 이용하여 데이터베이스와 연결하는 데이터소스를 생성
//		jsp의 Connection 객체를 생성한 것과 같음
		DataSource dataSource = new HikariDataSource(hikariConfig());
//		히카리CP 설정으로 데이터베이스와 정상적으로 연결되었는지 화면에 출력
		System.out.println(dataSource.toString());
		return dataSource;
	}

//	mybatis 를 사용하기 위한 설정
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//		스프링부트에서 마이바티스 사용 시 SqlSessionFactoryBean 클래스를 사용함
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		
//		히카리CP로 생성한 데이터 소스를 연결
		sqlSessionFactoryBean.setDataSource(dataSource);
//		마이바티스 xml 파일의 위치 및 이름을 설정
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/sql-*.xml"));
		sqlSessionFactoryBean.setTypeAliasesPackage("board.*");
		sqlSessionFactoryBean.setConfiguration(mybatisConfig());
		
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	@Bean
	@ConfigurationProperties(prefix="mybatis.configuration")
	public org.apache.ibatis.session.Configuration mybatisConfig() {
		return new org.apache.ibatis.session.Configuration();
	}
}









