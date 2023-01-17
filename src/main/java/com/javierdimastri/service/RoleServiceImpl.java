package com.javierdimastri.service;

import com.javierdimastri.model.ERole;
import com.javierdimastri.model.Role;
import com.javierdimastri.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role getRoleByName(ERole name) {
        return roleRepository.findByName(name);
    }
}
