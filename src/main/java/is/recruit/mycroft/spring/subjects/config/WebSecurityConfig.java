package is.recruit.mycroft.spring.subjects.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final JwtTokenProvider jwtTokenProvider;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable();
		httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).disable();
		httpSecurity.httpBasic().disable();
		httpSecurity
				.authorizeRequests()
				.antMatchers("/api/login/**").permitAll()
				.antMatchers("/api/reservation/**").authenticated()
				.antMatchers("/api/movies/**").authenticated()
				.antMatchers("/api/theater/**").authenticated()
				.antMatchers("/api/ticket/**").authenticated()
				.anyRequest().permitAll()
				.and().addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
	}

}
