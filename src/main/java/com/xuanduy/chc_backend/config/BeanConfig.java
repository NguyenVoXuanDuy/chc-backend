package com.xuanduy.chc_backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {
    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(9);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        //explanation for me to understand
        //DaoAuthenticationProvider is a class that implements AuthenticationProvider
        // is an interface that provides a method to authenticate for AuthenticationManager
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }


    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        //explanation for me to understand
        //AuthenticationConfiguration is a class that provides access to the AuthenticationManager
        //we need AuthenticationManager, and we do this for AuthenticationManager available in the IOC container
        //so that we can Dependency Inject it in our classes
        return authenticationConfiguration.getAuthenticationManager();
    }


}
