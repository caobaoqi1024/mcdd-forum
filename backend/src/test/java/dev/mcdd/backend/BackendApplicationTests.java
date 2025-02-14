package dev.mcdd.backend;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class BackendApplicationTests {

	@Autowired
	private ApplicationContext context;

	@Test
	void contextLoads() {
		Queue mailQueue = context.getBean("mailQueue", Queue.class);
		System.out.println("mailQueue = " + mailQueue);
	}

	@Test
	void securityPwd() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		boolean matches = encoder.matches("mcdd1024@pwd", "{pbkdf2@SpringSecurity_v5_8}62220177dcc48231c062c3af1a5afc42e7f5aa896f08881780d976784f211b4452fdabe7b046a547806160ea731fcff2");
		System.out.println("matches = " + matches);
	}

}
