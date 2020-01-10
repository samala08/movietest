package com.mytechladder.moviereview.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mytechladder.moviereview.model.Reviews;

public interface ReviewRepo extends CrudRepository<Reviews,Integer>{
	List<Reviews> findByUser_id(int user_id);
 	List<Reviews> findByMovie_id(int movie_id);
 	List<Reviews> findByRating(int rating);
 	List<Reviews> findByRatingAndMovie_idIn(int rating, List<Integer> movieIdList);
	
}
