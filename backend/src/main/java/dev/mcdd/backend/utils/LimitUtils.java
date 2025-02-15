package dev.mcdd.backend.utils;

import dev.mcdd.backend.common.Const;
import dev.mcdd.backend.env.properties.SecurityLimitConfigurationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LimitUtils {

	private final SecurityLimitConfigurationProperties properties;
	private final FlowUtils utils;

	/**
	 * 频率检测，防止用户高频申请Jwt令牌，并且采用阶段封禁机制
	 * 如果已经提示无法登录的情况下用户还在刷，那么就封禁更长时间
	 *
	 * @param userId 用户ID
	 * @return 是否通过频率检测
	 */
	public boolean frequencyCheck(int userId) {
		String key = Const.JWT_FREQUENCY + userId;
		return utils.limitOnceUpgradeCheck(key, properties.getFrequency(), properties.getBase(), properties.getUpgrade());
	}

}
