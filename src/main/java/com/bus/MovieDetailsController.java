package com.bus;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bus.beans.MovieDetails;
import com.bus.service.CustomerDao;

@Controller
public class MovieDetailsController {
    
	@Autowired
	private CustomerDao dao;

	 	@GetMapping("/movies")
	    public String showMovies(Model model) {
	        List<MovieDetails> movies = dao.getAllMovie();
	        model.addAttribute("movies", movies);
	        return "movies"; // Create a new HTML file named 'movies.html' to display movies
	    }
	    	    
	 	@PostMapping("/movies/add")
	    public String addMovie(@RequestParam("movieName") String movieName,
	                           @RequestParam("image") MultipartFile image,
	                           @RequestParam("movieDetails") String movieDetails) {
	        // Create MovieDetails object with the provided data
	        MovieDetails movie = new MovieDetails(movieName, image.getOriginalFilename(), movieDetails);
	        
	        // Save the movie
	        dao.saveMovie(movie);
	        
	        // Redirect to the movies page after adding a movie
	        return "redirect:/movies";
	    }

	    // Add other controller methods as needed: update, delete, etc.
}
