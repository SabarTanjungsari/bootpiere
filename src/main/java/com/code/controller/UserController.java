package com.code.controller;

import com.code.model.Role;
import com.code.model.User;
import com.code.service.RoleService;
import com.code.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = {"", "/"})
    public String getAlluser(Model model) {
        List<User> userList = userService.getAllUser();
        model.addAttribute("userList", userList);
        return "userList";
    }

    @RequestMapping(path = {"/add", "/edit/{id}"})
    public String addOrEdituser(Model model, @PathVariable("id") Optional<Long> id)  {
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
            System.out.println(bindingResult);
            System.out.println(user.isActive());
            return "userForm";
        } else {
            System.out.println(user.isActive());
            userService.saveUser(user);
        }

        return "redirect:/user/";
    }

    @RequestMapping(path = "/delete/{id}")
    public  String deleteuserById(Model model, @PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/user/";
    }
}
