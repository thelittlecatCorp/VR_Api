package kr.dbas.psychologyvrapi;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@SpringBootApplication
public class VrApiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(VrApiApplication.class, args);
	}

	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();

		sessionFactory.setDataSource(dataSource);
		
		// mapper.xml 의 resultType 패키지 주소 생략
		sessionFactory.setTypeAliasesPackage("kr.dbas.psychologyvrapi.vo");
		        
		// mybatis 설정 파일 세팅
		sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:/mybatis-config.xml"));
		
		sessionFactory.setMapperLocations(resolver.getResources("classpath:sql/*.xml"));
		return sessionFactory.getObject();
	}
}
