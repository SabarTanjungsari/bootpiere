package com.code.controller;

import com.code.exception.RecordNotFoundException;
import com.code.model.Role;
import com.code.service.RoleService;
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
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = { "/"})
    public String getAllRole(Model model) {
        List<Role> roleList = roleService.getAllRole();
        model.addAttribute("roleList", roleList);
        return "roleList";
    }

    @RequestMapping(path = {"/add", "/edit/{id}"})
    public String addOrEditRole(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
        if(id.isPresent()){
            Role role = roleService.getRoleById(id.get());
            model.addAttribute("role", role);
        } else {
            Role role = new Role();
            role.setActive(true);
            model.addAttribute("role", role);
        }
        return "roleForm";
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute @Valid Role role, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            System.out.println("BINDING RESULT ERROR");
            System.out.println(role.isActive());
            return "roleForm";
        } else {
            System.out.println(role.isActive());
            roleService.saveRole(role);
        }

        return "redirect:/role/";
    }

    @RequestMapping(path = "/delete/{id}")
    public  String deleteRoleById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
        roleService.deleteRoleById(id);
        return "redirect:/role/";
    }
}
