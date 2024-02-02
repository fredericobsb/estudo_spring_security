package com.estudo.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.httpBasic()
			.and()
			.authorizeHttpRequests()
			.anyRequest()
			/*.permitAll() ==> usado alternadamente com .authenticated, 
			 *      permiteAll deixa acessar todos endpoints.
			*/
			/*.authenticated() ==> permite acesso aos endpoints somente de usuarios autenticados.*/
			.permitAll()
			.and()
			.csrf().disable();
		    /* Veja que mesmo sem passar autenticacao no postman, a requisicao POST deu certo
		          porque o csrf foi desabilitado, e está sendo usado junto com .permiteAll. */
	}
}