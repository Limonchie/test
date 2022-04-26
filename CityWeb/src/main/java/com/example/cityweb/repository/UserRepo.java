package com.example.cityweb.repository;

import com.example.cityweb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
