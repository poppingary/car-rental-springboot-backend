package agk.wow.carrental.config;

import agk.wow.carrental.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	private static final String WORDS_START_WITH = "Bearer ";

	@Autowired
	private UserService userService;

	@Autowired
	private TokenUtil tokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		final String headerAuthorization = request.getHeader("Authorization");

		String jwtToken = this.getJwtTokenFromHeader(headerAuthorization);
		String username = this.getUsername(jwtToken);

		setUserAuthenticationToSpringSecurity(request, jwtToken, username);

		chain.doFilter(request, response);
	}

	private String getJwtTokenFromHeader(String headerAuthorization) {
		String token = null;

		if (headerAuthorization != null && headerAuthorization.startsWith(WORDS_START_WITH)) {
			token = headerAuthorization.substring(7);
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}

		return token;
	}

	private String getUsername(String token) {
		String username = null;

		try {
			username = this.tokenUtil.getUsernameFromToken(token);
		} catch (IllegalArgumentException e) {
			System.out.println("Unable to get JWT Token");
		} catch (ExpiredJwtException e) {
			System.out.println("JWT Token has expired");
		}

		return username;
	}

	private void setUserAuthenticationToSpringSecurity(HttpServletRequest request, String token, String username) {
		if (username == null || SecurityContextHolder.getContext().getAuthentication() != null) {
			return;
		}

		UserDetails userDetails = this.userService.loadUserByUsername(username);

		if (this.tokenUtil.validateToken(token, userDetails)) {
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
	}
}