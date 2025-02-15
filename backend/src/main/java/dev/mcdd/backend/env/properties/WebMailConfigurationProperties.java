package dev.mcdd.backend.env.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "web.mail")
public class WebMailConfigurationProperties {
	private Integer verifyLimit;
}
