package com.mytechladder.moviereview.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mytechladder.moviereview.model.Reviews;

public interface ReviewRepo extends CrudRepository<Reviews,Integer>{
	
}
