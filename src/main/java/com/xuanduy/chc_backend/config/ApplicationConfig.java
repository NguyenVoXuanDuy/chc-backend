package com.xuanduy.chc_backend.config;

import com.xuanduy.chc_backend.entity.Cart;
import com.xuanduy.chc_backend.entity.Role;
import com.xuanduy.chc_backend.entity.User;
import com.xuanduy.chc_backend.enums.RoleEnum;
import com.xuanduy.chc_backend.repository.CartRepository;
import com.xuanduy.chc_backend.repository.RoleRepository;
import com.xuanduy.chc_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationConfig implements WebMvcConfigurer {
    @NonFinal
    static final String ADMIN_USER_NAME = "admin@gmail.com";

    @NonFinal
    static final String ADMIN_PASSWORD = "admin";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final CartRepository cartRepository;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Allow all origins
                .allowedMethods("*") // Allow all methods (GET, POST, PUT, etc.)
                .allowedHeaders("*"); // Allow all headers
    }

    @Bean
    ApplicationRunner applicationRunner() {

        return args -> {
            if (userRepository.findByEmail(ADMIN_USER_NAME).isEmpty()) {


                roleRepository.save(Role.builder()
                        .name(RoleEnum.USER.getName())
                        .description(RoleEnum.USER.getDescription())
                        .build());

                Role adminRole = roleRepository.save(Role.builder()
                        .name(RoleEnum.ADMIN.getName())
                        .description(RoleEnum.ADMIN.getDescription())
                        .build());

                Set<Role> roles = new HashSet<Role>();
                roles.add(adminRole);

                Cart cart = new Cart();
                cart.setCartDetails(new HashSet<>());

                User user = User.builder()
                        .email(ADMIN_USER_NAME)
                        .password(passwordEncoder.encode(ADMIN_PASSWORD))
                        .firstName("Admin")
                        .lastName("Admin")
                        .roles(roles)
                        .phone("0123456789")
                        .orderRecords(new ArrayList<>())
                        .build();
                cart.setUser(user);
                userRepository.save(user);
                cartRepository.save(cart);

                log.warn("admin user has been created with default password: admin, please change it");
            }
            log.info("Application initialization completed .....");
        };
    }

}