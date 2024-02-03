package com.estudo.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfigV2 {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
		.httpBasic()
		.and()
		.authorizeHttpRequests()
		.antMatchers(HttpMethod.GET, "/api/v1/teste/**").permitAll() //qualquer usuario acessa metodos GET
		.antMatchers(HttpMethod.POST, "/api/v1/teste").hasRole("USER")//somente usuarios com perfil USER fazem POST
		.antMatchers(HttpMethod.DELETE, "/api/v1/teste/**").hasRole("ADMIN")//somente usuarios com perfil ADMIN fazem DELETE.
		/*.permitAll() ==> usado alternadamente com .authenticated, 
		 *      permiteAll deixa acessar todos endpoints.
		*/
		/*.authenticated() ==> permite acesso aos endpoints somente de usuarios autenticados.*/
		.anyRequest().authenticated()
		.and()
		.csrf().disable();
	    /* Veja que mesmo sem passar autenticacao no postman, a requisicao POST deu certo
	          porque o csrf foi desabilitado, e está sendo usado junto com .permiteAll. */
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
