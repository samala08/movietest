package com.mytechladder.moviereview.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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

	//Usecase to add reviews for a given movie by title
	@PostMapping(path="/addReview")
	public @ResponseBody Reviews addReview(@RequestParam String username, @RequestParam String title,
			@RequestParam String comment, @RequestParam int starrating)
	{			
		Movie movie= movierepo.findByTitle(title);
		int movie_id = movie.getId();
					
		User user=userrepo.findByUsername(username);
		int user_id = user.getId();
			
		Reviews review= new Reviews();
		review.setMovie_id(movie_id);
		review.setUser_id(user_id);
		review.setComment(comment);
		review.setRating(starrating);
		
		reviewrepo.save(review);
		
		return review;
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
	public List<Reviews> getMoviesByRatAndCat(@RequestParam( value ="rating") int rating, @RequestParam(value ="category") String category)
	{	
		if(rating < 1 || rating > 5) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid rating");
		}
	
		List<Movie> moviesByGivenCategory = movierepo.findByCategory(category);
		
		List<Integer> movieIdList = new ArrayList<Integer>();
		for(Movie mv : moviesByGivenCategory) {
				movieIdList.add(mv.getId());
		}
	
		List<Reviews> resultList = reviewrepo.findByRatingAndMovie_idIn(rating, movieIdList);	

		return resultList;
		
	};
	
	//Use case - task id 4: Request to read review of all movies by rating and movie_id
	@GetMapping(path = "/comment", params = { "rating" })
	public List<Reviews> findByRating(@RequestParam( value = "rating") int rating ) {
		
		List<Reviews> reviewsByRating = reviewrepo.findByRating(rating);
		
		List<Integer> movieIdList = new ArrayList<Integer>();
		for (Reviews rv: reviewsByRating) {
			movieIdList.add(rv.getMovie_id());
			
		}
		
		List<Reviews> resultList = reviewrepo.findByRatingAndMovie_idIn(rating, movieIdList);
		
		return resultList;
	}
		

}
