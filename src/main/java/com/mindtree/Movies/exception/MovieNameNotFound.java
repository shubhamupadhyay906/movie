package com.mindtree.Movies.exception;

public class MovieNameNotFound extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;

	public String getMessage() {
		return message;
	}

	public MovieNameNotFound(String message) {
		super();
		this.message = message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
