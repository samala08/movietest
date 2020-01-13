package com.mytechladder.moviereview.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mytechladder.moviereview.model.Movie;
import com.mytechladder.moviereview.model.Reviews;
import com.mytechladder.moviereview.model.User;
import com.mytechladder.moviereview.repository.MovieRepo;
import com.mytechladder.moviereview.repository.ReviewRepo;
import com.mytechladder.moviereview.repository.UserRepo;

@RestController
@RequestMapping("/movie")
public class ReviewController {

	@Autowired
	private ReviewRepo reviewrepo;
	@Autowired
	private MovieRepo movierepo;
	@Autowired
	private UserRepo userrepo;

	// Usecase(taskId)-1
	@PostMapping(path = "/comment")
	public @ResponseBody String addComments(@RequestParam String username, @RequestParam String title,
				@RequestParam String comment, @RequestParam int starrating) {

			return "Saved review";
	}
	
	//Use case - task id 5: Request to read review of all movies by given category 
	@GetMapping(path = "/comment")
	public List<Reviews> getReviewByCategory(@RequestParam String category) {
		List<Movie> moviesByGivenCategory = movierepo.findByCategory(category);
		List<Reviews> resultList = new ArrayList<Reviews>();
		for (Movie m: moviesByGivenCategory) {
			resultList.addAll(reviewrepo.findByMovie_id(m.getId()));
		}
		return resultList;
	}
	
	//Use case - task id 3: Request to read review of all movies by given rating and  category 
	@GetMapping(value = "/comment", params = { "rating", "category" })
	public List<Reviews> getMoviesByRatAndCat(@RequestParam( value ="rating") int rating, @RequestParam(value ="category") String category){
		// Get movies by category & prepare movie id list
		List<Movie> moviesByGivenCategory = movierepo.findByCategory(category);
		
		List<Integer> movieIdList = new ArrayList<Integer>();
		for(Movie mv : moviesByGivenCategory) {
			movieIdList.add(mv.getId());
		}
		
		// Get reviews by rating and prepared movie id list
		List<Reviews> result = reviewrepo.findByRatingAndMovie_idIn(rating, movieIdList);	

		return result;
	};
		

}
