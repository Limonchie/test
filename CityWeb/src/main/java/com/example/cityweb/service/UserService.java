package com.example.cityweb.service;

import com.example.cityweb.entity.Role;
import com.example.cityweb.entity.User;
import com.example.cityweb.exception.UserNotFoundException;
import com.example.cityweb.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserService{

    @Autowired
    private UserRepo userRepo;

    public List<User> listAll(){
        return(List<User>) userRepo.findAll();
    }

    public void save(User user){
        userRepo.save(user);
    }

    public User get(Long id) throws UserNotFoundException {
        Optional<User> result = userRepo.findById (id);
        if(result.isPresent ()) {
            return result.get();
        }
        throw new UserNotFoundException("Not find user");
    }

    public void delete(Long id){
        userRepo.deleteById (id);
    }

}
