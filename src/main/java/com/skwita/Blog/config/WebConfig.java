package com.skwita.Blog.config;

import com.skwita.Blog.entity.Role;
import com.skwita.Blog.entity.User;
import com.skwita.Blog.repository.RoleRepository;
import com.skwita.Blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Set;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    RoleRepository roleRepository;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    @Autowired
    public WebConfig(RoleRepository roleRepository,
                     UserRepository userRepository,
                     PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Bean
    public CommandLineRunner loadRoles() {
        if (roleRepository.findRoleByName("ROLE_ADMIN") == null) {
            roleRepository.save(new Role(1L, "ROLE_ADMIN"));
        }
        if (roleRepository.findRoleByName("ROLE_USER") == null) {
            roleRepository.save(new Role(2L, "ROLE_USER"));
        }
        return null;
    }

    @Bean
    public CommandLineRunner loadAdmin() {
        if (userRepository.findByUsername("admin") == null) {
            userRepository.save(
                    new User("admin",
                            passwordEncoder.encode("superAdmin123"),
                            Set.of(
                                    new Role(1L, "ROLE_ADMIN"),
                                    new Role(2L, "ROLE_USER")
                            )
                    )
            );
        }
        return null;
    }
}
