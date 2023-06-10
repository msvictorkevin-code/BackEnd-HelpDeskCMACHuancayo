package pe.cmac.huancayo.sistema.helpdesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@ComponentScan(basePackages = "pe.cmac.huancayo.sistema.helpdesk.dao.impl")
//@EnableJpaRepositories(basePackages = {"pe.cmac.huancayo.sistema.helpdesk.entity" })
//@EntityScan("pe.cmac.huancayo.sistema.helpdesk.entity")
@EnableAutoConfiguration(
		exclude = { HibernateJpaAutoConfiguration.class })

@SpringBootApplication
public class MsBackendSistemaHelpdeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsBackendSistemaHelpdeskApplication.class, args);
	}

}
