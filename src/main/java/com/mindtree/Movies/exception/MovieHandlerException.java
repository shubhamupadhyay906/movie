package com.mindtree.Movies.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.Movies.models.ApiError;


@RestControllerAdvice
public class MovieHandlerException {
	
	@ExceptionHandler(MovieNameNotFound.class)
	public ResponseEntity<ApiError> handleMovieNameNotFoundException(MovieNameNotFound movie){
		ApiError error=new ApiError(404,movie.getMessage(),new Date());
		return new ResponseEntity<ApiError>(error,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleNotFoundException(Exception ex){
		ApiError error=new ApiError(404,"Path variable should not be empty",new Date());
		return new ResponseEntity<ApiError>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
