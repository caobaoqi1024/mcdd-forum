package dev.mcdd.backend.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Slf4j
@Configuration
public class RabbitConfiguration {

	@Bean
	public Queue mailQueue() {
		log.info("init rabbitMQ with name mailQueue");
		return QueueBuilder
			.durable("mail")
			.build();
	}

}
