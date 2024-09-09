package com.micro.auth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AuthenticationConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeHttpRequests(
				request->
				request.anyRequest().authenticated())
		.httpBasic(Customizer.withDefaults());
		return httpSecurity.build() ;
	}
	
	@Bean
	UserDetailsService userDetailService() {
		UserDetails user1 = User.withUsername("username").password("passs").build();
		UserDetails user2 = User.withUsername("admin").password("admin").build();
		return new InMemoryUserDetailsManager(user1,user2);
		
	}

}
