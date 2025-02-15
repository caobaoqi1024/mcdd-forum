package dev.mcdd.backend.env.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "web.cors")
public class WebCorsConfigurationProperties {
	private String origin;
	private Boolean credentials;
	private String methods;
}
