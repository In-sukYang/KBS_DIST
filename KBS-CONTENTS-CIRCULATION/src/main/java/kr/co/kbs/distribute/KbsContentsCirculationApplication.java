package kr.co.kbs.distribute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@ImportResource("classpath:/config/schedule-config.xml")
public class KbsContentsCirculationApplication {

	public static void main(String[] args) {
		SpringApplication.run(KbsContentsCirculationApplication.class, args);
	}
}
