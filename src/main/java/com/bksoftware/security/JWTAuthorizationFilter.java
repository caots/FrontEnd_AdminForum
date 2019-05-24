package com.bksoftware.security;

import com.bksoftware.entities.user.AppUser;
import com.bksoftware.service_impl.user.AppUserService_Impl;
import com.bksoftware.service_impl.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    @Autowired
    private AppUserService_Impl appUserService;
    @Autowired
    private JWTService jwtService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    // dua user vao he thong
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(SecurityConstants.HEADER_STRING);
        if (header != null && header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        } else
            chain.doFilter(request, response);
    }


    // read token
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER_STRING);
        if (token != null) {
            String username = jwtService.decode(token);
            if (username != null) {
                AppUser appUser = appUserService.findByEmail(username);
                return new UsernamePasswordAuthenticationToken(username, null, appUser.getGrantedAuthorities());
            }
            return null;
        }
        return null;
    }


}
