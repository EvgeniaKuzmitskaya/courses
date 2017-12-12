package by.myProject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class LoginSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
        authenticationMgr.inMemoryAuthentication()
                    .withUser("admin").password("1111").authorities("ROLE_ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests()
                .antMatchers("/homePage").access("hasRole('ROLE_TEACHER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/studentPage").access("hasRole('ROLE_STUDENT')")
                .antMatchers("/teacherPage").access("hasRole('ROLE_TEACHER')")
                .and()
                .formLogin().loginPage("/loginForm")
                .defaultSuccessUrl("/homePage")
                .failureUrl("/loginForm?error\"")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/loginForm?logout\"");

    }
}




