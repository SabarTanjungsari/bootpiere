package com.code.audit;

import com.code.model.User;
import com.code.service.MyUserDetailsService;
import com.code.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware {
    @Autowired
    private UserService userService;

    @Override
    public Optional<Long> getCurrentAuditor() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            User loggedUser =  userService.findUserByName(username);
            return Optional.of(loggedUser.getId());
        } else {
            String username = principal.toString();
            User loggedUser =  userService.findUserByName(username);
            return Optional.of(loggedUser.getId());
        }
    }
}
