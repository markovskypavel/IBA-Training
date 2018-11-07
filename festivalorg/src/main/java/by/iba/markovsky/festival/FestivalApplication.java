package by.iba.markovsky.festival;

import by.iba.markovsky.festival.configuration.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("by.iba.markovsky.festival")
public class FestivalApplication {
	public static void main(String[] args) {
		SpringApplication.run(new Class[]{FestivalApplication.class, WebSecurityConfig.class}, args);
	}
}
