package tm.arzuv.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import tm.arzuv.app.Service.UserService;
import tm.arzuv.app.security.JwtAuthenticationFilter;
import tm.arzuv.app.security.JwtAuthorizationFilter;
import tm.arzuv.app.security.UserPrincipalDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private UserPrincipalDetailsService userPrincipalDetailsService;
	private UserService userService;

	@Autowired
	public WebSecurityConfig(UserPrincipalDetailsService userPrincipalDetailsService, UserService userService) {
		this.userPrincipalDetailsService = userPrincipalDetailsService;
		this.userService = userService;
	}

	private static final String ADMIN_ENDPOINT = "/admin/**";
	private static final String USER_ENDPOINT = "/documents/**";
	private static final String LOGIN_ENDPOINT =  "/login";

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			//remove csrf and state in session because in jwt we do not need them
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			//add jwt filters (1.authentication, 2.authorization)
			.addFilter(new JwtAuthenticationFilter(authenticationManager()))
			.addFilter(new JwtAuthorizationFilter(authenticationManager(), userService))
			.authorizeRequests()
			//configure access roles
			.antMatchers(HttpMethod.POST, LOGIN_ENDPOINT).permitAll()
			.antMatchers(USER_ENDPOINT).hasAuthority("USER")
			.antMatchers(ADMIN_ENDPOINT).hasAuthority("ADMIN");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);
		return daoAuthenticationProvider;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}