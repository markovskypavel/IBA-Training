package by.iba.markovsky.festival.configuration;

import by.iba.markovsky.festival.service.UserDetailsServiceImpl;
import by.iba.markovsky.festival.util.EncryptedPasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static by.iba.markovsky.festival.constant.MappingConstant.*;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Default admin role
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(EncryptedPasswordUtil.encrytePassword("admin"))
                .roles("ADMIN");
        // Setting Service to find User in the database. And Setting PassswordEncoder.
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        // The pages does not require login
        http.authorizeRequests().antMatchers(HOME, ABOUT_US, LOGIN, DENIED, NOT_FOUND, REGISTRATION, ERROR, GET_ACTIVITY, GET_ARTIST).permitAll();
        //For authenticated users
        http.authorizeRequests().antMatchers(SUBSCRIBE, UNSUBSCRIBE, LOGOUT).access("isAuthenticated()");

        // If no login, it will redirect to /login page.
        // For USER only.
        http.authorizeRequests().antMatchers(USER).access("hasAnyRole('USER')");

        // For ADMIN only.
        http.authorizeRequests().antMatchers(ADMIN, ADD_ACTIVITY, EDIT_ACTIVITY, DELETE_ACTIVITY,
                ADD_ARTIST, EDIT_ARTIST, DELETE_ARTIST, ADD_ACTIVITY_ARTIST, REMOVE_ACTIVITY_ARTIST, GET_UNUSED_ARTISTS, GET_USED_ARTISTS).access("hasRole('ADMIN')");

        // When the user has logged in as XX.
        // But access a page that requires role YY,
        // AccessDeniedException will be thrown.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage(DENIED);

        // Config for Login Form
        http.authorizeRequests().and().formLogin()
                // Submit URL of login page.
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage(LOGIN)
                .defaultSuccessUrl(HOME)
                .failureUrl(LOGIN + ERROR_QUERY)
                .usernameParameter("username")
                .passwordParameter("password")
                // Config for Logout Page
                .and().logout().logoutUrl(LOGOUT).logoutSuccessUrl(HOME);
    }

}
