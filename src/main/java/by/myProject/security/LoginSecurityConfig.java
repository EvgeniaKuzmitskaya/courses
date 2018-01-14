package by.myProject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class LoginSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
//
//
//    @Autowired
//    UserDetailsService userDetailsService;

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        // Users in memory.
//        auth.inMemoryAuthentication()
//                    .withUser("admin").password("1111").authorities("ROLE_ADMIN");
//
//// For User in database.
////        auth.userDetailsService(userDetailsService);
//    }

//    @Autowired
//    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//        auth.authenticationProvider(authenticationProvider());
//    }

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        // Users in memory.
        auth.inMemoryAuthentication()
                .withUser("admin").password("1111").authorities("ROLE_ADMIN");

        // For User in database.
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "SELECT user.login as username, user.password as password, user.enabled as enabled " +
                                "FROM user WHERE user.login=?")
                .authoritiesByUsernameQuery(
                        "select user.login as username, role.type_role as role " +
                        "FROM user INNER JOIN user_role ON user.id_user = user_role.id_user " +
                        "INNER JOIN role ON user_role.id_role = role.id_role " +
                        "WHERE user.login =?")
                .rolePrefix("ROLE_");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

              // The pages does not require login
                .antMatchers("/", "/loginForm").permitAll()

                // For ADMIN only.
                .antMatchers("/adminPage", "/registration", "/usersList").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/studentPage").access("hasRole('ROLE_STUDENT')")
                .antMatchers("/teacherPage").access("hasRole('ROLE_TEACHER')")
        .and()
                .formLogin()
                .successHandler(savedRequestAwareAuthenticationSuccessHandler())
                .loginPage("/loginForm")
                .loginProcessingUrl("/loginUrl")
//                .defaultSuccessUrl("/adminPage")
//                .defaultSuccessUrl("/teacherPage")
                .defaultSuccessUrl("/studentPage")


                .failureUrl("/loginForm?error")
                .usernameParameter("username").passwordParameter("password")
//все могут видеть форму для авторизации
                .permitAll()
        .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/loginForm?logout\"")
//        .and()
//                .rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository)
//                .tokenValiditySeconds(86400)

                // When the user has logged in as XX.
                // But access a page that requires role YY,
                // AccessDeniedException will throw.
        .and()
                .exceptionHandling().accessDeniedPage("/403")
        .and()
                .csrf()

        .and()
                .rememberMe().tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(1209600);

    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }

    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler
    savedRequestAwareAuthenticationSuccessHandler() {

        SavedRequestAwareAuthenticationSuccessHandler auth
                = new SavedRequestAwareAuthenticationSuccessHandler();
        auth.setTargetUrlParameter("targetUrl");
        return auth;
    }





}




