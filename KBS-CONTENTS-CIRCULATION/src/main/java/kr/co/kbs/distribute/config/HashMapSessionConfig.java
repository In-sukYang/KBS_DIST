package kr.co.kbs.distribute.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.session.ExpiringSession;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

@Configuration
@EnableSpringHttpSession
public class HashMapSessionConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	public SessionRepository<ExpiringSession> sessionRepository() {
		MapSessionRepository repository = new MapSessionRepository();
		Integer timeout = Integer.parseInt(env.getProperty("spring.session.timeout"));
		
		if (timeout != null) {
			repository.setDefaultMaxInactiveInterval(timeout);
		}
		return repository;
	}

}
