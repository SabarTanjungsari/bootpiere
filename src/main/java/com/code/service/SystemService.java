package com.code.service;

import com.code.model.System;
import com.code.model.User;
import com.code.repository.RoleRepository;
import com.code.repository.SystemRepository;
import com.code.repository.UserReporitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class SystemService {
    @Autowired
    SystemRepository systemRepository;

    public System getSystemById(Long id) {
        Optional<System> system = systemRepository.findById(id);
        if (system.isPresent()) {
            return system.get();
        }
        return new System();
    }

}
