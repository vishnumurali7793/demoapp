package com.project.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("hitler")
			.password("adolf123")
			.roles("ADMIN")
			.and()
			.withUser("marx")
			.password("karlmarx")
			.roles("USER")
			.and()
			.withUser("guevara")
			.password("ernesto")
			.roles("MANAGER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeHttpRequests()
		.antMatchers(HttpMethod.POST, "/v1.0/product/").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT, "/v1.0/product/*").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/v1.0/product/*").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/v1.0/sale/").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT, "/v1.0/sale/*").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/v1.0/sale/*").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/v1.0/revenue/**").hasRole("MANAGER")
		.antMatchers(HttpMethod.GET, "/v1.0/product").permitAll()
		.antMatchers(HttpMethod.GET, "/v1.0/product/*").permitAll()
		.antMatchers(HttpMethod.GET, "/v1.0/sale").permitAll()
		.antMatchers(HttpMethod.GET, "/v1.0/sale/*").permitAll()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().httpBasic();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
