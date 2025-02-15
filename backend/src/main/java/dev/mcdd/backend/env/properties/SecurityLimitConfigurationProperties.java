package dev.mcdd.backend.env.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "security.limit")
public class SecurityLimitConfigurationProperties {
	private Integer base;
	private Integer upgrade;
	private Integer frequency;
}
