package com.mindtree.Movies.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Movies implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int movieId;
	@Column(unique = true)
	private String name;
	private double boxOfficeCollection;
	private int rating;

	@ManyToMany(targetEntity = Directors.class,fetch = FetchType.EAGER)
	@JsonIgnoreProperties("movies")
	private List<Directors> director;

}
