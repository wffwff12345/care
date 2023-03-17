package com.freedom.config.filter;

import com.freedom.common.helper.JwtHelper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * JWT 认证 Filter
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private static final String USER_NAME = "user_name";

    private static final String USER_ROLE = "user_role";

    private static final String BEARER = "Bearer ";

    private static final String AUTHORIZATION = "Authorization";

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 获取 request 中的 Authorization
        String header = request.getHeader(AUTHORIZATION);
        if (header == null || !header.startsWith(BEARER)) {
            chain.doFilter(request, response);
            return;
        }
        Authentication authenticationToken = getUsernamePasswordAuthenticationToken(header);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String token) {
        Claims claims = JwtHelper.parseJWT(token.replace(BEARER, ""));
        if (claims == null) {
            return null;
        }
        // 获取 USER_NAME
        Object username = claims.get(USER_NAME);
        if (username == null) {
            return null;
        }
        // 获取 USER_ROLE
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        Object role = claims.get(USER_ROLE);
        if (role != null) {
            authorities.add(new SimpleGrantedAuthority(role.toString()));
        }
        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }
}
