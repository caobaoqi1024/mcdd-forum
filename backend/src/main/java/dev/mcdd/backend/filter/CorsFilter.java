package dev.mcdd.backend.filter;

import dev.mcdd.backend.common.Const;
import dev.mcdd.backend.env.properties.WebCorsConfigurationProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Order(Const.ORDER_CORS)
public class CorsFilter extends HttpFilter {

	private final WebCorsConfigurationProperties properties;

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException, ServletException, IOException {
		this.addCorsHeader(request, response);
		chain.doFilter(request, response);
	}

	/**
	 * 添加所有跨域相关响应头
	 *
	 * @param request  请求
	 * @param response 响应
	 */
	private void addCorsHeader(HttpServletRequest request, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", this.resolveOrigin(request));
		response.addHeader("Access-Control-Allow-Methods", this.resolveMethod());
		response.addHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
		if (properties.getCredentials()) {
			response.addHeader("Access-Control-Allow-Credentials", "true");
		}
	}

	/**
	 * 解析配置文件中的请求方法
	 *
	 * @return 解析得到的请求头值
	 */
	private String resolveMethod() {
		String methods = properties.getMethods();
		return methods.equals("*") ? "GET, HEAD, POST, PUT, DELETE, OPTIONS, TRACE, PATCH" : methods;
	}

	/**
	 * 解析配置文件中的请求原始站点
	 *
	 * @param request 请求
	 * @return 解析得到的请求头值
	 */
	private String resolveOrigin(HttpServletRequest request) {
		String origin = properties.getOrigin();
		return origin.equals("*") ? request.getHeader("Origin") : origin;
	}
}
