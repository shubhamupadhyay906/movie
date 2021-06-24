package com.mindtree.Movies.TestController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mindtree.Movies.controller.ControllerApp;
import com.mindtree.Movies.exception.MovieNameNotFound;
import com.mindtree.Movies.models.Directors;
import com.mindtree.Movies.models.Movies;
import com.mindtree.Movies.models.Responses;
import com.mindtree.Movies.service.MovieDirectorService;

@SpringBootTest
public class TestMovieController {

	@Mock
	MovieDirectorService movieService;

	@InjectMocks
	ControllerApp movieController;

	@Test
	void testGetMovies() throws MovieNameNotFound {
		List<Movies> movies = new ArrayList<Movies>();
		List<Directors> directors = new ArrayList<Directors>();
		directors.add(new Directors(1, "shubham", 15, "male", 10, null));
		movies.add(new Movies(1, "king", 21345, 3, directors));
		movies.add(new Movies(2, "lucy", 21345, 4, directors));
		ResponseEntity<List<Movies>> response = new ResponseEntity<List<Movies>>(movies, HttpStatus.OK);
		when(movieService.getMovies()).thenReturn(movies);
		assertEquals(response, movieController.getMovies());
	}

	@Test
	void testAddNewMovie() throws MovieNameNotFound {

		Movies movie = new Movies(1, "king", 2134, 4, null);
		doNothing().when(movieService).addNewMovies(movie);
		ResponseEntity<Movies> responseEntity = new ResponseEntity<Movies>(movie, HttpStatus.CREATED);
		Responses response = new Responses("success", responseEntity);
		movieController.addNewMovies(movie);
		verify(movieService, times(1)).addNewMovies(movie);
	}
	
	@Test
	void testGetDirectorDetailsByMovieName() throws MovieNameNotFound {
		
		String name = "king";
		Movies movie = new Movies(1, "king", 2134, 4, null);
		ResponseEntity<Movies> response=new ResponseEntity<>(movie, HttpStatus.OK);
		when(movieService.getDirectorDetailsByMovieName(name)).thenReturn(movie);
		assertEquals(response,movieController.getDirectorDetailsByMovieName(name));
	}
	
	@Test
	void testDeleteMovie() throws MovieNameNotFound {
		String name = "king";
		Movies movie = new Movies(1, "king", 2134, 4, null);
		ResponseEntity<Movies> response=new ResponseEntity<>(movie, HttpStatus.OK);
		doNothing().when(movieService).deleteMovie(name);
		movieController.deleteMovie(name);
		verify(movieService,times(1)).deleteMovie(name);
	}

}
