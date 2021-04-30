package com.jxw.icharity.repository;

import com.jxw.icharity.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User,Integer> {

    Integer countUserByUsername(String username);

    User getById(Integer id);

    User getUserByUsername(String username);

}
