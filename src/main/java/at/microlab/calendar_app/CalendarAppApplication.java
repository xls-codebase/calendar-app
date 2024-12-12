package at.microlab.calendar_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CalendarAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalendarAppApplication.class, args);
	}

}
