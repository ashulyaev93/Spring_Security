package com.geekbrains.spring.lesson8.configs;

import com.geekbrains.spring.lesson8.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity//(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true )
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;

    @Autowired
    public void setUserDetailsService(UserService userService) {
        this.userService = userService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/products/").hasAnyRole("ADMIN", "SUPER_ADMIN", "MANAGER")
                .antMatchers("/admin/").hasRole("ADMIN")
                .antMatchers("/cart/createOrder*").authenticated()
                .antMatchers("/registration*").anonymous()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .logout() // This is missing and is important
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login");
    }


//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{noop}11")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2y$12$kppL/79H63sx3NoXlZhY/uDW2EiB18ByX8YeENyFwyxAnHjrCT4pK")
//                .roles("USER", "ADMIN")
//                .build();
//
//        UserDetails sa = User.builder()
//                .username("sa")
//                .password("{bcrypt}$2y$12$kppL/79H63sx3NoXlZhY/uDW2EiB18ByX8YeENyFwyxAnHjrCT4pK")
//                .roles("USER", "ADMIN", "SUPER_ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin, sa);
//    }

//    @Bean
//    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{noop}11")
//                .authorities("ROLE_USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2y$12$kppL/79H63sx3NoXlZhY/uDW2EiB18ByX8YeENyFwyxAnHjrCT4pK")
//                .authorities("ROLE_USER", "ROLE_ADMIN")
//                .build();
//
//        UserDetails sa = User.builder()
//                .username("sa")
//                .password("{bcrypt}$2y$12$kppL/79H63sx3NoXlZhY/uDW2EiB18ByX8YeENyFwyxAnHjrCT4pK")
//                .authorities("ROLE_USER", "ROLE_ADMIN", "ROLE_SUPER_ADMIN")
//                .build();
//
//        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
//
//        if(!manager.userExists(user.getUsername())){
//            manager.createUser(user);
//        }
//        if(!manager.userExists(admin.getUsername())){
//            manager.createUser(admin);
//        }
//        if(!manager.userExists(sa.getUsername())){
//            manager.createUser(sa);
//        }
//        return manager;
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }

}
