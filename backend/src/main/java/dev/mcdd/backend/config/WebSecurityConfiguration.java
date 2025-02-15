package dev.mcdd.backend.config;

import dev.mcdd.backend.common.Const;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
			.requestMatchers("/api/auth/**", "/error").permitAll()
			.requestMatchers("/images/**").permitAll()
			.requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
			.requestMatchers("/api/admin/**").hasRole(Const.ROLE_ADMIN)
			.anyRequest().hasAnyRole(Const.ROLE_DEFAULT, Const.ROLE_ADMIN));
		http.headers(Customizer.withDefaults());
		http.sessionManagement(Customizer.withDefaults());
		http.formLogin(Customizer.withDefaults());
		http.anonymous(Customizer.withDefaults());
		http.csrf(Customizer.withDefaults());
		http.userDetailsService(inMemoryUserDetailsService());
		return http.build();
	}

	public UserDetailsService inMemoryUserDetailsService() {
		User.UserBuilder users = User.builder();
		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
		userDetailsManager.createUser(users.username("admin")
			.password("{noop}admin")
			.roles("ADMIN")
			.build());
		userDetailsManager.createUser(users.username("user")
			.password("{noop}user")
			.roles("USER")
			.build());
		return userDetailsManager;
	}


}
