package dev.mcdd.backend.config;

import dev.mcdd.backend.env.properties.WebCorsConfigurationProperties;
import dev.mcdd.backend.filter.CorsFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class WebConfiguration implements WebMvcConfigurer {

	private final WebCorsConfigurationProperties properties;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

//	@Bean
//	public FilterRegistrationBean<CorsFilter> corsFilter() {
//		FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
//		registrationBean.setFilter(new CorsFilter(properties));
//		registrationBean.setOrder(Integer.MIN_VALUE);
//		registrationBean.setUrlPatterns(Set.of("/*"));
//		return registrationBean;
//	}

}
