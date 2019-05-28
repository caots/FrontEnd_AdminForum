package com.bksoftware.security;


import com.bksoftware.service_impl.UserDetailsService_Impl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService_Impl userDetailsService;

    public SecurityConfig(UserDetailsService_Impl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public JWTAuthorizationFilter jwtAuthorizationFilter() throws Exception {
        return new JWTAuthorizationFilter(authenticationManager());
    }

    @Bean
    public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();

//        http.antMatcher("/api/**/private/**")
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic().authenticationEntryPoint(restAuthenticationEntryPoint())
//                .and()
//                .addFilter(jwtAuthorizationFilter())
//                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
        http.antMatcher("/api/**")

                .authorizeRequests()
                .antMatchers("/api/**/public/**").permitAll()
                .antMatchers("/api/**/public/register").permitAll()
                .antMatchers("/api/**/admin/**").hasAuthority("ADMIN")
                .antMatchers("/api/**/mod/**").hasAuthority("MOD")
                .antMatchers("/api/**/user/**").hasAuthority("USER")
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new HeaderFilter(authenticationManager()),HeaderFilter.class)
                .httpBasic().authenticationEntryPoint(restAuthenticationEntryPoint())
                .and()
                .addFilter(jwtAuthorizationFilter())
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());


        //stateless
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("api/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

}