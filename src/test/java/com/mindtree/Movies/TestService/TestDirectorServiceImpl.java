package com.mindtree.Movies.TestService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mindtree.Movies.Repository.DirectorsRepository;
import com.mindtree.Movies.exception.DirectorNameNotFound;
import com.mindtree.Movies.models.Directors;
import com.mindtree.Movies.models.Movies;
import com.mindtree.Movies.serviceImpl.MovieDirectorServiceImpl;


@SpringBootTest
public class TestDirectorServiceImpl {
	
	@Mock
	DirectorsRepository directorsRepository;
	
	@InjectMocks
	MovieDirectorServiceImpl directorServiceImpl;
	
	@Test
	void testGetDirectors() {
		List<Directors> directors = new ArrayList<Directors>();
		directors.add(new Directors(1, "shubham", 22, "male", 10, null));
		directors.add(new Directors(2, "upadhyay", 22, "male", 15, null));
		
		when(directorsRepository.findAll()).thenReturn(directors);
		assertEquals(directors, directorServiceImpl.getDirectors());
	}
	
	@Test
	void testPostaddNewDirectors() throws DirectorNameNotFound {
		Directors directors = new Directors(1, "shubham", 23, "male", 4, null);
		when(directorsRepository.save(directors)).thenReturn(null);
		directorServiceImpl.addNewDirectors(directors);
		verify(directorsRepository, times(1)).save(directors);
	}
	
	@Test
	void testGetDirectorByName() throws DirectorNameNotFound {
		String name = "shubham";
		Directors directors = new Directors(1, "shubham", 23, "male", 4, null);
		when(directorsRepository.findDirectorByName(name)).thenReturn(directors);
		Directors director = directorServiceImpl.getDirector(name);
		assertEquals(directors.getName(), director.getName());
	}
	
	@Test
	void testupdateDirector() throws DirectorNameNotFound {
		Directors director = new Directors(1, "shubham", 24, "male", 5, null);
		Directors dir = new Directors(1, "shubham", 23, "male", 4, null);
		String name = "shubham";
		when(directorsRepository.findDirectorByName(name)).thenReturn(director);
		when(directorsRepository.save(director)).thenReturn(dir);
		assertEquals(dir,directorServiceImpl.updateDirector(dir));
	}
}
