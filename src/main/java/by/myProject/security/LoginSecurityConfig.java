package by.myProject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class LoginSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    RoleBaseUrlHandler urlHandler;


    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        // Users in memory.
        auth.inMemoryAuthentication()
                .withUser("admin").password("1111").authorities("ROLE_ADMIN");

        // For User in database.
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "SELECT user.username as username, user.password as password, user.enabled as enabled " +
                                "FROM user WHERE user.username=?")
                .authoritiesByUsernameQuery(
                        "select user.username as username, role.type_role as role " +
                        "FROM user INNER JOIN user_role ON user.id_user = user_role.id_user " +
                        "INNER JOIN role ON user_role.id_role = role.id_role " +
                        "WHERE user.username=?")
                .rolePrefix("ROLE_");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter,CsrfFilter.class);

        http.authorizeRequests()
              // The pages does not require login
                .antMatchers("/", "/loginPage").permitAll()
                // For role only.
                .antMatchers("/adminPage", "/registration", "/usersList", "courseAdd", "courseAdded").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/studentPage", "/courseStudent", "/addOnCourse").access("hasRole('ROLE_STUDENT')")
                .antMatchers("/teacherPage", "/courseTeacher", "/resultCourse").access("hasRole('ROLE_TEACHER')")
        .and()
                .formLogin()
                .successHandler(urlHandler)
                .loginPage("/loginPage")
                .loginProcessingUrl("/loginUrl")
//                .defaultSuccessUrl("/adminPage")
//                .defaultSuccessUrl("/teacherPage")
//                .defaultSuccessUrl("/studentPage")
                .failureUrl("/loginPage?error")
                .usernameParameter("userName").passwordParameter("password")
//все могут видеть форму для авторизации
//                .permitAll()
        .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/loginPage?logout\"")
                .deleteCookies("JSESSIONID")
                .permitAll()

                // When the user has logged in as XX.
                // But access a page that requires role YY,
                // AccessDeniedException will throw.
        .and()
                .exceptionHandling().accessDeniedPage("/403")
        .and()
                .csrf()

        .and()
                .rememberMe()
                .rememberMeParameter("_spring_security_remember_me")
                .rememberMeCookieName("course_remember_me")
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(1209600);

    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }

}




