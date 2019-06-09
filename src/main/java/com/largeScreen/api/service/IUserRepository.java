package com.largeScreen.api.service;

import com.largeScreen.api.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
