package com.code.service;

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

    public User getUserById(Long id) {
        Optional<User> user = userReporitory.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        return new User();
    }

    public User saveUser(User newUser) {
        String encodedString = Base64.getEncoder().encodeToString(newUser.getEmailpassword().getBytes());
        String encryptPassword = Base64.getEncoder().encodeToString(newUser.getPassword().getBytes());
        if (newUser.getId() == null) {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            newUser.setEmailpassword(encodedString);
            newUser.setPasswordEncrypt(encryptPassword);
            newUser = userReporitory.save(newUser);
            return newUser;
        } else {
            Optional<User> user = userReporitory.findById(newUser.getId());
            if (user.isPresent()) {
                User updateUser = user.get();
                updateUser.setName(newUser.getName());
                updateUser.setEmail(newUser.getEmail());
                updateUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
                updateUser.setEmailpassword(encodedString);
                updateUser.setPasswordEncrypt(encryptPassword);
                updateUser.setActive(newUser.isActive());

                newUser = userReporitory.save(updateUser);
                return newUser;
            } else {
                newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
                newUser.setEmailpassword(encodedString);
                newUser.setPasswordEncrypt(encryptPassword);
                newUser = userReporitory.save(newUser);
                return newUser;
            }
        }
    }

    public void deleteUserById(Long id){
        Optional<User> User = userReporitory.findById(id);
        if (User.isPresent()) {
            userReporitory.deleteById(id);
        }
    }

    public User findUserByName(String name) {
        return userReporitory.findByname(name);
    }
}
