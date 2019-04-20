package my.microservice.customermanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "my.microservice.customermanagement.persistence")
public class TestConfig {



}
