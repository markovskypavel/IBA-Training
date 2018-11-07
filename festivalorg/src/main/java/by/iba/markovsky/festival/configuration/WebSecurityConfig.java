package by.iba.markovsky.festival.configuration;

import by.iba.markovsky.festival.service.UserDetailsServiceImpl;
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
        // Setting Service to find User in the database. And Setting PassswordEncoder.
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        //TODO: Правильно ностроить маппинг
        // The pages does not require login
        http.authorizeRequests().antMatchers(HOME, ABOUT_US, LOGIN, DENIED, NOT_FOUND, REGISTRATION).permitAll();
        //For authenticated users
        http.authorizeRequests().antMatchers(SUBSCRIBE, UNSUBSCRIBE, LOGOUT).access("isAuthenticated()");

        // If no login, it will redirect to /login page.
        // For USER only.
        http.authorizeRequests().antMatchers(USER).access("hasAnyRole('USER', 'ADMIN')");

        // For ADMIN only.
        http.authorizeRequests().antMatchers(ADMIN, ADD_FESTIVAL, ADD).access("hasRole('ADMIN')");

        // When the user has logged in as XX.
        // But access a page that requires role YY,
        // AccessDeniedException will be thrown.
        //TODO: установить обработчик
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
