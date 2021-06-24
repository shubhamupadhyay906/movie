package com.mindtree.Movies.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mindtree.Movies.Repository.DirectorsRepository;
import com.mindtree.Movies.Repository.MovieRepository;
import com.mindtree.Movies.controller.ControllerApp;
import com.mindtree.Movies.exception.DirectorNameNotFound;
import com.mindtree.Movies.exception.MovieNameNotFound;
import com.mindtree.Movies.models.Directors;
import com.mindtree.Movies.models.Movies;
import com.mindtree.Movies.service.MovieDirectorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MovieDirectorServiceImpl implements MovieDirectorService {

	@Autowired
	private DirectorsRepository daoDirectors;

	@Autowired
	private MovieRepository daoMovies;

	@Override
	public void addNewMovies(Movies movies) throws MovieNameNotFound {
		Optional<Movies> movie = Optional.ofNullable(daoMovies.findMovieByName(movies.getName()));
		if (movie.isPresent()) {
			throw new MovieNameNotFound("Movie exists Add new movie");
		}
		daoMovies.save(movies);
	}

	@Override
	public void addNewDirectors(Directors directors) throws DirectorNameNotFound {
		// TODO Auto-generated method stub
		Optional<Directors> director = Optional.ofNullable(daoDirectors.findDirectorByName(directors.getName()));
		if (director.isPresent()) {
			throw new DirectorNameNotFound("Director exist add new one");
		}
		daoDirectors.save(directors);
	}

	@Override
	@Cacheable(cacheNames = "allmovies")
	public List<Movies> getMovies() {
		log.info("from DB");
		// TODO Auto-generated method stub
		return daoMovies.findAll();
	}

	@Override
	@Cacheable(cacheNames = "alldirector")
	public List<Directors> getDirectors() {
		log.info("from DB");
		// TODO Auto-generated method stub
		return daoDirectors.findAll();
	}

	@Override
	@Cacheable(cacheNames = "director", key = "#name")
	public Directors getDirector(String name) throws DirectorNameNotFound {
		log.info("from DB");
		Optional<Directors> directors = Optional.ofNullable(daoDirectors.findDirectorByName(name));
		if (directors.isPresent()) {
			Directors director = directors.get();
			return director;
		} else {
			log.error("error occured");
			throw new DirectorNameNotFound("Director " + name + "not found");
		}
	}

	@Override
	@Cacheable(cacheNames = "movies", key = "#name")
	public Movies getDirectorDetailsByMovieName(String name) throws MovieNameNotFound {
		log.info("fetching from DB");
		Optional<Movies> movies = Optional.ofNullable(daoMovies.findMovieByName(name));
		if (movies.isPresent()) {

			Movies movie = movies.get();
			return movie;
		} else {
			log.error("Movie not found");
			throw new MovieNameNotFound("Movie " + name + " not found");
		}
	}

	@Override
	@CachePut(cacheNames = "director", key = "#director.name")
	public Directors updateDirector(Directors directors) throws DirectorNameNotFound {
		Directors dir = daoDirectors.findDirectorByName(directors.getName());
		if (dir == null) {
			throw new DirectorNameNotFound("Director not found");
		} else if (dir.getAge() == directors.getAge() || dir.getCount() == directors.getCount()) {
			throw new DirectorNameNotFound("Make a new update old data repeated");
		}
		dir.setAge(directors.getAge());
		dir.setCount(directors.getCount());
		return daoDirectors.save(dir);
	}

	@Override
	@CacheEvict(cacheNames = "movies", key = "#name")
	public void deleteMovie(String name) throws MovieNameNotFound {
		log.info("fetching from DB");
		Optional<Movies> movies = Optional.ofNullable(daoMovies.findMovieByName(name));
		if (movies.isPresent()) {
			daoMovies.deleteByName(name);
		} else {
			log.error("Movie not found");
			throw new MovieNameNotFound("Movie " + name + " not found");
		}

	}

}
