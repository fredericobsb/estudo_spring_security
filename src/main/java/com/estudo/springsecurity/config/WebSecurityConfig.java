package com.estudo.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	/*
	@Autowired
	private UserDetailsService userDetailsService;
	*/
	
	private final UserDetailsServiceImpl  userDetailsService;
	
	public WebSecurityConfig(UserDetailsServiceImpl userDetailsService){
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
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
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
		/*
		auth.inMemoryAuthentication()
				.withUser("root")
				.password(passwordEncoder().encode("sigedoc2023"))
				.roles("ADMIN");
	   */
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
