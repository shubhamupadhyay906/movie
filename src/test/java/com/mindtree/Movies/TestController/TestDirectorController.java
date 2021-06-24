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
import com.mindtree.Movies.exception.DirectorNameNotFound;
import com.mindtree.Movies.models.Directors;
import com.mindtree.Movies.service.MovieDirectorService;

@SpringBootTest
public class TestDirectorController {
	
	@Mock
	MovieDirectorService directorService;
	
	@InjectMocks
	ControllerApp directorController;
	
	@Test
	void testGetDirectors() throws DirectorNameNotFound {
		List<Directors> directors = new ArrayList<Directors>();
		directors.add(new Directors(1, "shubham", 15, "male", 10, null));
		directors.add(new Directors(2, "shubham", 15, "male", 10, null));
		directors.add(new Directors(3, "shubham", 15, "male", 10, null));
		ResponseEntity<List<Directors>> response = new ResponseEntity<List<Directors>>(directors, HttpStatus.OK);
		when(directorService.getDirectors()).thenReturn(directors);
		assertEquals(response, directorController.getDirectors());
		
	}
	
	@Test
	void testAddNewDirectors() throws DirectorNameNotFound {
		Directors directors = new Directors(1, "shubham", 15, "male", 10, null);
		doNothing().when(directorService).addNewDirectors(directors);
		directorController.addNewDirectors(directors);
		verify(directorService,times(1)).addNewDirectors(directors);
	}
	
	@Test
	void testGetDirector() throws DirectorNameNotFound {
		String name = "shubham";
		Directors directors = new Directors(1, "shubham", 15, "male", 10, null);
		ResponseEntity<Directors> response=new ResponseEntity<>(directors, HttpStatus.OK);
		when(directorService.getDirector(name)).thenReturn(directors);
		assertEquals(response,directorController.getDirector(name));
	}
	
	@Test
	void testUpdateDirector() throws DirectorNameNotFound {
		String name="shubham";
		Directors director=new Directors(4,"Akhilesh",(byte)32,"male",(byte)4,null);
		ResponseEntity<Directors> response=new ResponseEntity<>(director,HttpStatus.OK);
		when(directorService.updateDirector(director)).thenReturn(director);
		assertEquals(response,directorController.updateDirector(director));
	}
	
	

}
