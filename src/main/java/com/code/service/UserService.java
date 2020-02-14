package com.code.service;

import com.code.exception.RecordNotFoundException;
import com.code.model.Role;
import com.code.model.User;
import com.code.repository.RoleRepository;
import com.code.repository.UserReporitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    UserReporitory userReporitory;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getAllUser() {
        List<User> UserList = (List<User>) userReporitory.findAll();
        if (UserList.size() > 0) {
            return UserList;
        } else {
            return new ArrayList<User>();
        }
    }

    public User getUserById(Long id) throws RecordNotFoundException {
        Optional<User> user = userReporitory.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RecordNotFoundException("No User record exist for given id");
        }
    }

    public User saveUser(User newUser) {
        if (newUser.getId() == null) {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            Role userRole = roleRepository.findByName("ADMIN");
            newUser.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
            newUser = userReporitory.save(newUser);
            return newUser;
        } else {
            Optional<User> user = userReporitory.findById(newUser.getId());
            if (user.isPresent()) {
                User updateUser = user.get();
                updateUser.setName(newUser.getName());
                updateUser.setEmail(newUser.getEmail());
                updateUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
                updateUser.setActive(newUser.isActive());

                Role userRole = roleRepository.findByName("ADMIN");
                newUser.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

                newUser = userReporitory.save(newUser);
                return newUser;
            } else {
                newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
                Role userRole = roleRepository.findByName("ADMIN");
                newUser.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

                newUser = userReporitory.save(newUser);
                return newUser;
            }
        }
    }

    public void deleteUserById(Long id) throws RecordNotFoundException {
        Optional<User> User = userReporitory.findById(id);
        if (User.isPresent()) {
            userReporitory.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

    public User findUserByName(String name) {
        return userReporitory.findByname(name);
    }
}
