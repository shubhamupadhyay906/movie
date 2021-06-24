package com.mindtree.Movies.TestService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.mindtree.Movies.Repository.MovieRepository;
import com.mindtree.Movies.exception.MovieNameNotFound;
import com.mindtree.Movies.models.Movies;
import com.mindtree.Movies.serviceImpl.MovieDirectorServiceImpl;

@SpringBootTest
public class TestMovieServiceImpl {

	@Mock
	MovieRepository movieRepository;

	@InjectMocks
	MovieDirectorServiceImpl movieServiceImpl;

	@Test
	void testGetMovies() {
		List<Movies> movies = new ArrayList<Movies>();
		movies.add(new Movies(1, "king", 231546, 4, null));
		when(movieRepository.findAll()).thenReturn(movies);
		assertEquals(movies, movieServiceImpl.getMovies());
	}

	@Test
	void testAddNewMovies() throws MovieNameNotFound {
		Movies movies = new Movies(1, "king", 1213, 3, null);
		when(movieRepository.save(movies)).thenReturn(null);
		movieServiceImpl.addNewMovies(movies);
		verify(movieRepository, times(1)).save(movies);
	}
	
	@Test
	void testGetDirectorDetailsByMovieName() throws MovieNameNotFound {
		String name = "king";
		Movies movies = new Movies(1, "king", 1213, 3, null);
		when(movieRepository.findMovieByName(name)).thenReturn(movies);
		Movies movie = movieServiceImpl.getDirectorDetailsByMovieName(name);
		assertEquals(movies.getName(), movie.getName());
	}
	
	@Test
	void testDeleteMovie() throws MovieNameNotFound {
		String name = "king";
		Movies movies = new Movies(1, "king", 1213, 3, null);
		when(movieRepository.findMovieByName(name)).thenReturn(movies);
		movieServiceImpl.deleteMovie(name);
		verify(movieRepository,times(1)).deleteByName(name);
	}
	

}
