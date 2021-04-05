package com.ex.musicdb.config;

import com.ex.musicdb.service.impl.MusicDBUserService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MusicDBUserService musicDbUserService;
    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(MusicDBUserService musicDbUserService, PasswordEncoder passwordEncoder) {
        this.musicDbUserService = musicDbUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //allow access to static resources to eny one;
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
//                .antMatchers("/js/**", "/css/**", "/images/**").permitAll()
                //allow access to index, user login end registration to eny one;
                .antMatchers("/", "/users/login", "/users/register").permitAll()
                //protect oll other pages;
                .antMatchers("/**")
                .authenticated()
                //configure login with HTML form;
                .and()
                .formLogin()
                // our login page will be served by the controller with mapping /user/login
                .loginPage("/users/login")
                // the name of the user name input field in OUR login form is username (optional)
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                // the name of the user password input field in OUR login form is password (optional)
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                // if success redirect here;
                .defaultSuccessUrl("/home")
                // if not success redirect here;
                .failureForwardUrl("/users/login-error")
                // which endpoints performs logout, e.g. http://localhost:8080/loout(!this shut bi POST request)
                .and().logout()
                //where to land after logout
                .logoutUrl("/logout").logoutSuccessUrl("/")
                // remove the session from the server
                .invalidateHttpSession(true)
                //delete the session cookie
                .deleteCookies("JSESSIONID"); // bye! :-)

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(musicDbUserService).
                passwordEncoder(passwordEncoder);
    }
}
