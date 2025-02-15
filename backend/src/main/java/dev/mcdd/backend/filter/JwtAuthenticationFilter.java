package dev.mcdd.backend.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import dev.mcdd.backend.common.Const;
import dev.mcdd.backend.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 用于对请求头中Jwt令牌进行校验的工具，为当前请求添加用户验证信息
 * 并将用户的ID存放在请求对象属性中，方便后续使用
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtUtils jwtUtils;
	private final StringRedisTemplate template;

	@Override
	protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
		String authorization = request.getHeader("Authorization");
		DecodedJWT jwt = jwtUtils.resolveJwt(authorization);
		if (jwt != null) {
			UserDetails user = jwtUtils.toUser(jwt);
			if (!template.hasKey(Const.BANNED_BLOCK + jwtUtils.toId(jwt))) {
				UsernamePasswordAuthenticationToken authentication =
					new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				request.setAttribute(Const.ATTR_USER_ID, jwtUtils.toId(jwt));
			} else {
				jwtUtils.invalidateJwt(authorization);
			}
		}
		filterChain.doFilter(request, response);
	}
}
