package hello.itemservice;

import hello.itemservice.config.*;
import hello.itemservice.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


//@Import(MemoryConfig.class) //메모리 참조
//@Import(JdbcTemplateV1Config.class) //JdbcTemplate 사용
//@Import(JdbcTemplateV2Config.class) //NamedJdbcTemplate 사용함
//@Import(JdbcTemplateV3Config.class) //NamedJdbcTemplate 사용 SimpleJdbcInsert사용함
//@Import(MyBatisConfig.class) //mybatis 사용
//@Import(JpaConfig.class) //JPA 사용
@Slf4j
@Import(SpringDataJpaConfig.class) //SpringData JPA 사용
@SpringBootApplication(scanBasePackages = "hello.itemservice.web")
public class ItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

	@Bean
	@Profile("local")
	public TestDataInit testDataInit(ItemRepository itemRepository) {
		return new TestDataInit(itemRepository);
	}

/*	등록 불 필요
	@Bean
	@Profile("test")
	public DataSource dataSource(){
		log.info("메모리 데이터베이스 초기화");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}*/

}
