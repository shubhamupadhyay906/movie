package com.mindtree.Movies.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.Movies.models.Movies;

@Repository
@Transactional
public interface MovieRepository extends JpaRepository<Movies, Integer>{

	public Movies findMovieByName(String name);

	
	 void deleteByName(String name);


}
