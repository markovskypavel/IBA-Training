package by.iba.markovsky.festival;

import by.iba.markovsky.festival.constant.ConfigConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(ConfigConstant.BASE_PACKAGE)
public class FestivalApplication {
	public static void main(String[] args) {
		SpringApplication.run(FestivalApplication.class, args);
	}
}
