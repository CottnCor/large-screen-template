package com.largeScreen.api.service;

import com.largeScreen.api.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
