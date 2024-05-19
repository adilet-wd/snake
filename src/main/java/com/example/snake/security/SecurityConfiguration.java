package com.example.snake.security;

import com.example.snake.security.services.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    public CustomUserDetailService userDetailService;

    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity httpSecurity)throws Exception{
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> {
            registry.requestMatchers("/swagger-ui/**").permitAll();
            registry.requestMatchers("/hello","/register/").permitAll();
            registry.requestMatchers("/user/**").hasRole("USER");
            registry.requestMatchers("/admin/**").hasRole("ADMIN");
            registry.anyRequest().permitAll();
        })
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .build();

    }

//    @Bean
//    public UserDetailsService UserDetailsService(){
//        UserDetails normalUser = User.builder()
//                .username("aaa")
//                .password("$2a$12$alsaOKsClQP5hyBCzARjxudKHlrua9v/m2DR99FyXvvka.tZxIr1S")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("aaaa")
//                .password("$2a$12$0BAetdikASfUNEMJYSY6e.ORFoXTA2e8HqHQBcR6bL.NvSvaY0fUW")
//                .roles("ADMIN", "USER")
//                .build();
//        return new InMemoryUserDetailsManager(normalUser, admin);
//    }

    @Bean
    public UserDetailsService UserDetailsService(){
        return userDetailService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(PasswordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder PasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
