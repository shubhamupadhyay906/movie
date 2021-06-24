package com.mindtree.Movies.service;


import java.util.List;

import com.mindtree.Movies.exception.DirectorNameNotFound;
import com.mindtree.Movies.exception.MovieNameNotFound;
import com.mindtree.Movies.models.Directors;
import com.mindtree.Movies.models.Movies;

public interface MovieDirectorService {
	
	public void addNewMovies(Movies movies) throws MovieNameNotFound;
	
	public void addNewDirectors(Directors directors) throws DirectorNameNotFound;

	public List<Movies> getMovies();
	
	public List<Directors> getDirectors();
	
	public Directors getDirector(String name) throws DirectorNameNotFound;
	
	public Movies getDirectorDetailsByMovieName(String name) throws MovieNameNotFound;
	
	public void deleteMovie(String name) throws MovieNameNotFound;
	
	public Directors updateDirector(Directors directors) throws DirectorNameNotFound;
}
