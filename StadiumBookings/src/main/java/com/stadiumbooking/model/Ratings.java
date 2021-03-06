package com.stadiumbooking.model;

import java.util.Objects;

public class Ratings {

	private int reviewID;
	private int userId;
	private String 	reviews;
	private double ratingValue ;
	private int stadiumId;
	private User user;
	
	public Ratings() {
		super();

	}
	
	
	
	public Ratings(int reviewID, int userId, String reviews, double ratings, int stadiumId) {
		super();
		this.reviewID = reviewID;
		this.userId = userId;
		this.reviews = reviews;
		this.ratingValue = ratings;
		this.stadiumId = stadiumId;
	}
	public int getReviewID() {
		return reviewID;
	}
	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getReviews() {
		return reviews;
	}
	public void setReviews(String reviews) {
		this.reviews = reviews;
	}

	public double getRatingValue() {
		return ratingValue;
	}

	public void setRatingValue(double ratingValue) {
		this.ratingValue = ratingValue;
	}



	public int getStadiumId() {
		return stadiumId;
	}



	public void setStadiumId(int stadiumId) {
		this.stadiumId = stadiumId;
	}



	public Ratings(int reviewID, String reviews, double ratingValue, int stadiumId, User user) {
		super();
		this.reviewID = reviewID;
		this.reviews = reviews;
		this.ratingValue = ratingValue;
		this.stadiumId = stadiumId;
		this.user = user;
	}



	public Ratings(int userId2, String review, Double rating, int stadiumId2) {
		super();
		this.userId = userId2;
		this.reviews = review;
		this.ratingValue =rating;
		this.stadiumId = stadiumId2;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	@Override
	public int hashCode() {
		return Objects.hash(reviewID, reviews, stadiumId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ratings other = (Ratings) obj;
		return reviewID == other.reviewID && Objects.equals(reviews, other.reviews) && stadiumId == other.stadiumId;
	}
	@Override
	public String toString() {
		return "Ratings [reviewID=" + reviewID + ", userId=" + userId + ", reviews=" + reviews + ", ratings=" + ratingValue
				+ ", stadium_id=" + stadiumId + "]";
	}
	
	
	
}
