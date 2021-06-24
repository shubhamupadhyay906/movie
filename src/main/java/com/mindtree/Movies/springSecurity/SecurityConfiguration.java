package com.mindtree.Movies.springSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	 UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				
				.antMatchers("/getdirectors","/getDirectors","/displayDirectors.js"
						,"/searchDirector","/getDirectorByName/{name}","searchDirector.js"
						,"/searchMovie","/getDirectorDetailsByMovieName/{name}","/searchMovie.js").hasAnyRole("USER","ADMIN")
				.antMatchers("/addNewDirector","/postDirectors.js","directors"
						,"/addNewMoviesWithDirectors","/postMovieByDirector.js","/PostMovies"
						,"/DeleteMovie","/deleteMovieByName/{name}","/deleteMovie.js"
						,"/updateDirectors","/updateDirectorsAgeAndAwardCount/{name}"
						,"/updateDirector.js","/UpdateGet.js").hasRole("ADMIN")
				.antMatchers("/").permitAll()
				.and().formLogin().and().csrf().disable();
				
	}
	
	@Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
	
}