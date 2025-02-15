package dev.mcdd.backend.config;

import dev.mcdd.backend.env.properties.DataMinioConfigurationProperties;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MinioConfiguration {

	private final DataMinioConfigurationProperties properties;

	@Bean
	public MinioClient minioClient() {
		log.info("init minio client...");
		return MinioClient.builder()
			.endpoint(properties.getUrl())
			.credentials(properties.getUsername(), properties.getPassword())
			.build();
	}

}
