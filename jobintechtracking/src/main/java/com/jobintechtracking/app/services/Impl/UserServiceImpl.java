package com.jobintechtracking.app.services.Impl;

import com.jobintechtracking.app.entities.Users;
import com.jobintechtracking.app.repositories.UserRepository;
import com.jobintechtracking.app.services.facade.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userrepository;

    @Override
    public List<Users> findAll() {
        return userrepository.findAll();
    }

    @Override
    @Transactional
    public Users save(Users users) {
        return userrepository.save(users);
    }

    @Override
    public Users findUserById(Long id) {
        return userrepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
         userrepository.deleteById(id);
    }
}
