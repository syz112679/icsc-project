package ee4216.backend.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.GET;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
    
	@Autowired
	DataSource dataSource;
 
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
            
            auth.jdbcAuthentication().dataSource(dataSource)
                    .passwordEncoder(new BCryptPasswordEncoder())
                    .usersByUsernameQuery("select username,password, enabled from users where username=?")
                    .authoritiesByUsernameQuery("select username, role from user_roles where username=?");
	}
 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
            http
                .authorizeRequests()
                    .antMatchers(POST,"/").permitAll()
                    .antMatchers(GET, "/").hasRole("USER")
                    .antMatchers(GET, "/wishlist").hasRole("USER")
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login")
                    .permitAll();
            
            http.exceptionHandling().accessDeniedPage("/403");
            
	}
    
}