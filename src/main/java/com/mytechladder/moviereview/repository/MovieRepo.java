package com.mytechladder.moviereview.repository;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

import com.mytechladder.moviereview.model.Movie;

public interface MovieRepo extends CrudRepository<Movie,Integer> {
	
	Movie findByTitle(String title);
	List<Movie> findByCategory(String title);

}
