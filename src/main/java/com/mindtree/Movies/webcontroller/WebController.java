package com.mindtree.Movies.webcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

	@GetMapping("/")
	public String Home() {
		return "Home";
	}

	@GetMapping("/PostMovies")
	public String PostMovies() {
		return "PostMovies";
	}

	@GetMapping("/directors")
	public String directors() {
		return "directors";
	}
	
	@GetMapping("/getdirectors")
	public String getDirectors() {
		return "getDirectors";
	}
	
	@GetMapping("/searchDirector")
	public String searchDirector() {
		return "searchDirector";
	}
	
	@GetMapping("/searchMovie")
	public String searchMovie() {
		return "searchMovie";
	}
	
	@GetMapping("/updateDirectors")
	public String updateDirector() {
		return "updateDirector";
	}
	
	@GetMapping("/DeleteMovie")
	public String DeleteMovie() {
		return "DeleteMovie";
	}
	
	

//	@GetMapping("/login")
//	public String login() {
//		return "login";
//	}

//	@GetMapping("/")
//	public String home() {
//		return "index";
//	}

}
