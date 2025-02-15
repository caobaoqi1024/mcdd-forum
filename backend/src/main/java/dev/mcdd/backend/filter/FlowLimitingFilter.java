package dev.mcdd.backend.filter;

import dev.mcdd.backend.common.Const;
import dev.mcdd.backend.common.RestBean;
import dev.mcdd.backend.env.properties.WebFlowConfigurationProperties;
import dev.mcdd.backend.utils.FlowUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 限流控制过滤器
 * 防止用户高频请求接口，借助Redis进行限流
 */
@Slf4j
@Component
@RequiredArgsConstructor
@Order(Const.ORDER_FLOW_LIMIT)
public class FlowLimitingFilter extends HttpFilter {

	private final WebFlowConfigurationProperties properties;
	private final StringRedisTemplate template;
	private final FlowUtils flowUtils;

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		String address = request.getRemoteAddr();
		if (!"OPTIONS".equals(request.getMethod()) && !tryCount(address))
			this.writeBlockMessage(response);
		else
			chain.doFilter(request, response);
	}

	/**
	 * 尝试对指定IP地址请求计数，如果被限制则无法继续访问
	 *
	 * @param address 请求IP地址
	 * @return 是否操作成功
	 */
	private boolean tryCount(String address) {
		synchronized (address.intern()) {
			if (Boolean.TRUE.equals(template.hasKey(Const.FLOW_LIMIT_BLOCK + address)))
				return false;
			String counterKey = Const.FLOW_LIMIT_COUNTER + address;
			String blockKey = Const.FLOW_LIMIT_BLOCK + address;
			return flowUtils.limitPeriodCheck(counterKey, blockKey, properties.getBlock(), properties.getLimit(), properties.getPeriod());
		}
	}

	/**
	 * 为响应编写拦截内容，提示用户操作频繁
	 *
	 * @param response 响应
	 * @throws IOException 可能的异常
	 */
	private void writeBlockMessage(HttpServletResponse response) throws IOException {
		response.setStatus(429);
		response.setContentType("application/json;charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.write(RestBean.failure(429, "请求频率过快，请稍后再试").asJsonString());
	}
}

