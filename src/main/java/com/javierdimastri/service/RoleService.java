package com.javierdimastri.service;

import com.javierdimastri.model.ERole;
import com.javierdimastri.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Role getRoleByName(ERole name);

    List<Role> assignRolesByName(Set<String> rolesName);
}
