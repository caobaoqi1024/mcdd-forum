package dev.mcdd.backend.env.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "data.minio")
public class DataMinioConfigurationProperties {
	private String url;
	private String username;
	private String password;
}
