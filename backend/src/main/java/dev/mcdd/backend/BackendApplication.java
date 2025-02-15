package dev.mcdd.backend;

import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.util.Map;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
@ConfigurationPropertiesScan("dev.mcdd.backend.env.properties")
public class BackendApplication implements ApplicationRunner {

	private final ServletContext context;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		log.info("Backend started");
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Map<String, ? extends FilterRegistration> map = context.getFilterRegistrations();
		map.values().forEach(filterRegistration -> {
			System.out.println(filterRegistration.getName());
		});
	}
}
