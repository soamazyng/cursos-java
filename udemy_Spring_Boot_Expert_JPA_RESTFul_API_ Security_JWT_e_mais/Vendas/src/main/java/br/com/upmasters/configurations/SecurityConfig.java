package br.com.upmasters.configurations;

import br.com.upmasters.domain.service.impl.UsuarioServiceImpl;
import br.com.upmasters.security.jwt.JwtAuthFilter;
import br.com.upmasters.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String ADMIN = "ADMIN";
  private static final String USER = "USER";

  @Autowired
  private UsuarioServiceImpl usuarioService;

  @Autowired
  private JwtService jwtService;

  @Bean
  public OncePerRequestFilter jwtFilter() {
    return new JwtAuthFilter(jwtService, usuarioService);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .userDetailsService(usuarioService)
        .passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/api/clientes/**")
        .hasAnyRole(ADMIN, USER)
        .antMatchers("/api/produtos/**")
        .hasRole(ADMIN)
        .antMatchers("/api/pedidos/**")
        .hasAnyRole(ADMIN, USER)
        .antMatchers(HttpMethod.GET, "/api/usuarios/**")
        .hasRole(ADMIN)
        .antMatchers(HttpMethod.POST, "/api/usuarios/**")
        .permitAll()
        .anyRequest().authenticated()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers(
        "/v2/api-docs",
        "/v3/api-docs",
        "/configuration/ui",
        "/swagger-ui/**",
        "/swagger-resources/**",
        "/configuration/security",
        "/swagger-ui.html",
        "/webjars/**");
  }
}