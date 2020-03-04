package com.code.controller;

import com.code.model.Role;
import com.code.model.User;
import com.code.service.MailService;
import com.code.service.RoleService;
import com.code.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    MailService mailService;

    @RequestMapping(value = {"", "/"})
    public String getAlluser(Model model) {
        List<User> userList = userService.getAllUser();
        model.addAttribute("userList", userList);
        return "userList";
    }

    @RequestMapping(path = {"/add", "/edit/{id}"})
    public String addOrEditUser(Model model, @PathVariable("id") Optional<Long> id)  {
        List<Role> roleList = roleService.getAllRole();
        model.addAttribute("roleList", roleList);
        if(id.isPresent()){
            User user = userService.getUserById(id.get());
            model.addAttribute("user", user);
        } else {
            User user = new User();
            user.setActive(true);
            model.addAttribute("user", user);
        }
        return "userForm";
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute @Valid User user, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            List<Role> roleList = roleService.getAllRole();
            model.addAttribute("roleList", roleList);
            return "userForm";
        } else {
            userService.saveUser(user);

            Base64.Decoder decoder = Base64.getDecoder();
            String pwdDecode = new String(decoder.decode(user.getPasswordEncrypt()));
            String sendTo = user.getEmail();
            String mailSubject = "User Registration Information !";
            String mailBody = "Your User : " + user.getName() + "\nYour Password : " + pwdDecode;

            mailService.sendMail(mailSubject, mailBody, sendTo);

        }

        return "redirect:/user/";
    }

    @RequestMapping(path = {"/detail/{id}"})
    public String detailUser(Model model, @PathVariable("id") Optional<Long> id)  {
        User user = userService.getUserById(id.get());
        model.addAttribute("user", user);

        Set<Role> userRoles = user.getRoles();
        model.addAttribute("userRoles", userRoles);

        return "userDetail";
    }

    @RequestMapping(path = "/delete/{id}")
    public  String deleteuserById(Model model, @PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/user/";
    }

}
