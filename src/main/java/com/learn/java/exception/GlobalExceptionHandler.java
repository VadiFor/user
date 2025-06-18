package com.learn.java.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Map<String, String>> entityNotFound(EntityNotFoundException ex) {
		log.info(ex.getMessage());
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(Map.of("error", ex.getMessage()));
	}
	
	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<Map<String, String>> duplicateResource(DuplicateResourceException ex) {
		log.info(ex.getMessage());
		return ResponseEntity
				.status(HttpStatus.CONFLICT)
				.body(Map.of("error", ex.getMessage()));
	}
}
