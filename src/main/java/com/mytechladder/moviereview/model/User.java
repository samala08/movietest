/**
 * 
 */
package com.mytechladder.moviereview.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author samala
 *
 */

@Entity
public class User {

	@Id
	private int id;
	private String username;

	public User(int id, String username) {
		super();
		this.id = id;
		this.username = username;
	}

	protected User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getusername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}

}
