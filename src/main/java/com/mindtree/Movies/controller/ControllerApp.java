package com.mindtree.Movies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.Movies.exception.DirectorNameNotFound;
import com.mindtree.Movies.exception.MovieNameNotFound;
import com.mindtree.Movies.models.Directors;
import com.mindtree.Movies.models.Movies;
import com.mindtree.Movies.models.Responses;
import com.mindtree.Movies.service.MovieDirectorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
//@RequestMapping("/dir")
public class ControllerApp {

	@Autowired
	private MovieDirectorService service;

	// movies
	@GetMapping("/getmovies")
	public ResponseEntity<List<Movies>> getMovies() throws MovieNameNotFound {
		List<Movies> movies = service.getMovies();
		if (movies.isEmpty()) {
			log.error("No Movie record found");
			throw new MovieNameNotFound("Movie list is empty ");
		}
		log.info("Data Retrieved ");
		return new ResponseEntity<List<Movies>>(movies, HttpStatus.OK);
	}

	// Movies
	@PostMapping("/addNewMoviesWithDirectors")
	public Responses addNewMovies(@RequestBody Movies movies) throws MovieNameNotFound {
		if (movies.getName().equals("") || movies.getName() == null | movies.getRating() == 0) {
			log.error("error");
			throw new MovieNameNotFound("Field's can't be empty");
		}
		service.addNewMovies(movies);
		ResponseEntity<Movies> responseEntity = new ResponseEntity<Movies>(movies, HttpStatus.CREATED);
		Responses response = new Responses("success", responseEntity);
		log.info("new moview with director added");
		return response;
	}

	// Movie
	@GetMapping("/getDirectorDetailsByMovieName/{name}")
	public ResponseEntity<Movies> getDirectorDetailsByMovieName(@Validated @PathVariable String name)
			throws MovieNameNotFound {
		if (name == null || name.equals("")) {
			throw new MovieNameNotFound("Movie name not found" + name);
		}
		Movies movie = this.service.getDirectorDetailsByMovieName(name);
		log.info("retrived");
		return new ResponseEntity<Movies>(movie, HttpStatus.OK);

	}

	// Movies
	@DeleteMapping("/deleteMovieByName/{name}")
	public ResponseEntity deleteMovie(@Validated @PathVariable String name) throws MovieNameNotFound {
		if (name == null || name.equals("")) {
			log.error("error");
			throw new MovieNameNotFound("Movie name not found" + name);
		} else {
			service.deleteMovie(name);
			log.info("Movie deleted");
			return new ResponseEntity(name + " deleted", HttpStatus.OK);
		}
	}

	// Director
	@GetMapping("/getDirectors")
	public ResponseEntity<List<Directors>> getDirectors() throws DirectorNameNotFound {
		List<Directors> directors = service.getDirectors();
		if (directors.isEmpty()) {
			log.error("No director's found");
			throw new DirectorNameNotFound("Director list is empty");
		}
		log.info("Data Retrieved ");
		return new ResponseEntity<List<Directors>>(directors, HttpStatus.OK);
	}

	// Director
	@PostMapping("/addNewDirector")
	public Responses addNewDirectors(@RequestBody Directors directors) throws DirectorNameNotFound {
		if (directors.getName() == null || directors.getName().equals("") || directors.getGender() == null
				|| directors.getGender().equals("") || directors.getAge() == 0) {
			log.error("error");
			throw new DirectorNameNotFound("Field's can't be empty");
		}
		service.addNewDirectors(directors);
		log.info("New director added");
		ResponseEntity<Directors> responseEntity = new ResponseEntity<Directors>(directors, HttpStatus.CREATED);
		Responses response = new Responses("success", responseEntity);
		return response;
	}

	// Director
	@GetMapping("/getDirectorByName/{name}")
	public ResponseEntity<Directors> getDirector(@PathVariable String name) throws DirectorNameNotFound {
		if (name == null || name.equals("")) {
			throw new DirectorNameNotFound("Director name not found" + name);
		}
		Directors director = this.service.getDirector(name);
		log.info("retrived");
		return new ResponseEntity<Directors>(director, HttpStatus.OK);
	}

	// director
	@PutMapping("/updateDirectorsAgeAndAwardCount/{name}")
	public ResponseEntity<Directors> updateDirector(@RequestBody Directors directors) throws DirectorNameNotFound {
		if (directors.getName() == null || directors.getName().equals("") || directors.getGender() == null
				|| directors.getAge() == 0) {
			log.error("Error occured");
			throw new DirectorNameNotFound("Field's can't be empty");
		} else {
			Directors updateDirector = service.updateDirector(directors);
			log.info("updated successfully");
			return new ResponseEntity<Directors>(updateDirector, HttpStatus.OK);
		}

	}

}
