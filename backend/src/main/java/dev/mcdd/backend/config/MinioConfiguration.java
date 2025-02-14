package dev.mcdd.backend.config;

import io.minio.MinioClient;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioConfiguration {

	private String url;
	private String username;
	private String password;

	@Bean
	public MinioClient minioClient() {
		log.info("init minio client with url {}", url);
		return MinioClient.builder()
			.endpoint(url)
			.credentials(username, password)
			.build();
	}

}
