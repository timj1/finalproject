package com.buutcamp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource secDataSource;

    //this method defines who are allowed to use our application
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(secDataSource);

        /*
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(users.username("Dan").password("test123").roles("visitor"))
                .withUser(users.username("Miro").password("test123").roles("visitor","manager"));
                */
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()

                //defines URL's to have a role requirement
                .antMatchers("/updatemessage").hasRole("visitor")
                .antMatchers("/postmessage").authenticated()
                .antMatchers("/manager").hasRole("manager")
                .and()

                ////allow people to login
               .formLogin()
                    .loginPage("/")
                    .loginProcessingUrl("/authenticateUser")
                    .permitAll()
                .and()

                //allow people to logout
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/logout.done").deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                    .permitAll()
                .and()
                //Customize the "forbidden" page
                .exceptionHandling()
                    .accessDeniedPage("/forbidden");
    }

    @Bean
    public UserDetailsManager userDetailsManager() {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(secDataSource);

        return jdbcUserDetailsManager;
    }




    /**
     *
     * --Database table and user creation--
     *
     *Create table final_project.users (
     * 	username varchar(50) primary key,
     *     	`password` varchar(68),
     *      	enabled tinyint(1));
     *
     * Create table final_project.authorities(
     *      	username varchar(50),
     *      	authority varchar(50),
     * 	FOREIGN KEY(username) REFERENCES final_project.users(username));
     *
     * Create table final_project.messages (
     * 	id int NOT NULL AUTO_INCREMENT,
     * 	date_create DATETIME DEFAULT CURRENT_TIMESTAMP,
     * 	date_update DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     * 	title varchar(45),
     * 	message varchar(600),
     * 	username varchar(50),
     * 	PRIMARY KEY (id),
     * 	FOREIGN KEY (username) REFERENCES final_project.users(username));
     *
     *
     *insert into security_demo.users values("Dan",
     * 	"{bcrypt}$2a$04$DCHOe9nWjT.iwL5ChrjhJ.okY3mR9iANdSji3Vies7D8yM8gWfluq",1);
     *
     * insert into security_demo.users values("Miro",
     * 	"{bcrypt}$2a$04$DCHOe9nWjT.iwL5ChrjhJ.okY3mR9iANdSji3Vies7D8yM8gWfluq",1);
     *
     * insert into security_demo.authorities value("Dan","ROLE_visitor");
     * insert into security_demo.authorities value("Miro","ROLE_visitor");
     * insert into security_demo.authorities value("Miro","ROLE_manager");
     *
     *
     */
}
