package com.code.service;

import com.code.exception.RecordNotFoundException;
import com.code.model.Role;
import com.code.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRole() {
        List<Role> roleList = (List<Role>) roleRepository.findAll();
        if (roleList.size() > 0) {
            return roleList;
        } else {
            return new ArrayList<Role>();
        }
    }

    public Role getRoleById(Long id) throws RecordNotFoundException {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            return role.get();
        } else {
            throw new RecordNotFoundException("No role record exist for given id");
        }
    }

    public Role saveRole(Role newRole) {
        if (Long.valueOf(newRole.getId()) == null) {
            newRole = roleRepository.save(newRole);
            return newRole;
        } else {
            Optional<Role> role = roleRepository.findById(newRole.getId());
            if (role.isPresent()) {
                Role updateRole = role.get();
                updateRole.setName(newRole.getName());
                updateRole.setDescription(newRole.getDescription());
                updateRole.setActive(newRole.isActive());

                newRole = roleRepository.save(newRole);
                return newRole;
            } else {
                newRole = roleRepository.save(newRole);
                return newRole;
            }
        }
    }

    public void deleteRoleById(Long id) throws RecordNotFoundException {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            roleRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
}
