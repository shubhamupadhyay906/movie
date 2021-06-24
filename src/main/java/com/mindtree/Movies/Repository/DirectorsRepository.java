package com.mindtree.Movies.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.Movies.models.Directors;

@Repository
public interface DirectorsRepository extends JpaRepository<Directors, Integer>{

	public Directors findDirectorByName(String name);

	

}
