package dev.mcdd.backend.env.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "security.jwt")
public class SecurityJwtConfigurationProperties {
	private String key;
	private Integer expire;
}
