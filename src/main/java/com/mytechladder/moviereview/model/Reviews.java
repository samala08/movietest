package com.mytechladder.moviereview.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reviews {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="movie_id")
	private int movie_id;
	
	
	@Column(name="user_id")
	private int user_id;
	
	private String comment;
	private int rating;

	@ManyToOne
	@JoinColumn(name = "movie_id",referencedColumnName="id", insertable = false, updatable = false)
	private Movie movie;


	@ManyToOne
	@JoinColumn(name = "user_id",referencedColumnName="id",insertable = false, updatable = false)
	private User user;



	public Reviews() {
	}
	
	public Reviews(int movie_id, int user_id, String comment, int rating, Movie movie, User user) {
		super();
		this.movie_id = movie_id;
		this.user_id = user_id;
		this.comment = comment;
		this.rating = rating;
		this.movie = movie;
		this.user = user;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMovie_id() {
		return movie_id;
	}

	
	public int getUser_id() {
		return user_id;
	}
	
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}


}
