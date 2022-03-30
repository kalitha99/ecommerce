package com.ecommerce.sample.service;



import com.ecommerce.sample.model.Role;
import com.ecommerce.sample.repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role createNewRole(Role role) {

        return roleDao.save(role);
    }
}
