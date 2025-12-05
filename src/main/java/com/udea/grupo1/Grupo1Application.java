package com.udea.grupo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication(exclude = {
    org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class,
    org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration.class
})
@ComponentScan(basePackages = "com.udea.grupo1",
    excludeFilters = @ComponentScan.Filter(
        type = FilterType.REGEX,
        pattern = "com\\.udea\\.grupo1\\.controller\\.v1\\..*|com\\.udea\\.grupo1\\.service\\.WeatherService|com\\.udea\\.grupo1\\.repository\\.(City|Weather)Repository"
    )
)
public class Grupo1Application {

	public static void main(String[] args) {
		SpringApplication.run(Grupo1Application.class, args);
	}

}
