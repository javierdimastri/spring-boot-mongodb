package com.javierdimastri.service;

import com.javierdimastri.model.ERole;
import com.javierdimastri.model.Role;
import com.javierdimastri.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role getRoleByName(ERole name) {
        return roleRepository.findByName(name);
    }

    private List<Role> handleNonEmptyRoles(Set<String> rolesName){
        List<Role> roles = new ArrayList<>();
        rolesName.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = this.getRoleByName(ERole.ROLE_ADMIN);
                    roles.add(adminRole);
                    break;
                case "mod":
                    Role moderatorRole = this.getRoleByName(ERole.ROLE_MODERATOR);
                    roles.add(moderatorRole);
                    break;
                default:
                    Role userRole = this.getRoleByName(ERole.ROLE_USER);
                    roles.add(userRole);
            }
        });
        return roles;
    }

    @Override
    public List<Role> assignRolesByName(Set<String> rolesName) {
        List<Role> roles = new ArrayList<>();
        if (rolesName == null) {
            Role userRole = this.getRoleByName(ERole.ROLE_USER);
            roles.add(userRole);
            return roles;
        }
        return this.handleNonEmptyRoles(rolesName);
    }
}
