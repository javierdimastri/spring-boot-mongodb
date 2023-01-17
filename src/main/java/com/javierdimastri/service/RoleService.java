package com.javierdimastri.service;

import com.javierdimastri.model.ERole;
import com.javierdimastri.model.Role;

public interface RoleService {
    Role getRoleByName(ERole name);
}
