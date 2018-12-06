package com.afkl.interview.fareinfo.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

import com.afkl.interview.fareinfo.exceptions.TravelApiException;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(Throwable.class)
	public HttpEntity handleGlobalException(Throwable t) {
//        log.error("Unable to process request.", t);
		return new ResponseEntity(SERVICE_UNAVAILABLE);
	}

	@ExceptionHandler(HttpServerErrorException.class)
	public HttpEntity handleGlobalException(HttpServerErrorException e) {
		return new ResponseEntity(e.getStatusCode());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public HttpEntity handleBadRequest() {
		return new ResponseEntity(BAD_REQUEST);
	}

	@ExceptionHandler(TravelApiException.class)
	public HttpEntity handleGlobalException(TravelApiException e) {
		return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
