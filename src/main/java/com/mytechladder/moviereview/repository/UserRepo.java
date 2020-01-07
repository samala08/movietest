package com.mytechladder.moviereview.repository;

import org.springframework.data.repository.CrudRepository;

import com.mytechladder.moviereview.model.User;

public interface UserRepo extends CrudRepository<User, Integer> {

	User findByUsername(String userName);
}
