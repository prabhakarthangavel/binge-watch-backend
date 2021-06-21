package com.mobileapp.bingewatch.modals;

import java.util.Date;

public class AddPost {
	private String user;
	private String movie_id;
	private Date post_date;
	private int stars;
	private char movie_like;
	private String review;
	private String tags;
	private String movie_name;
	private String movie_img;
	private int year;
	private String cast;
	public AddPost() {
		
	}
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
	}
	public Date getPost_date() {
		return post_date;
	}
	public void setPost_date(Date post_date) {
		this.post_date = post_date;
	}
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	public char getMovie_like() {
		return movie_like;
	}
	public void setMovie_like(char movie_like) {
		this.movie_like = movie_like;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public String getMovie_img() {
		return movie_img;
	}
	public void setMovie_img(String movie_img) {
		this.movie_img = movie_img;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getCast() {
		return cast;
	}
	public void setCast(String cast) {
		this.cast = cast;
	}
}
