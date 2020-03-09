package com.code.configuration;

import com.code.model.User;
import com.code.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfiguration {

    @Autowired
    UserService userService;

    @Bean
    public AuditorAware<String> auditorProvider(){
        /*
          if you are using spring security, you can get the currently logged username with following code segment.
          SecurityContextHolder.getContext().getAuthentication().getName()
          return ()-> Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
         */

        return ()-> Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
    }


}
