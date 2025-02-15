package dev.mcdd.backend.env.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "web.flow")
public class WebFlowConfigurationProperties {
	private Integer limit;
	private Integer period;
	private Integer block;
}
