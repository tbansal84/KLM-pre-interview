package com.afkl.interview.fareinfo.domain.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;

import com.afkl.interview.fareinfo.dtos.TripDetails;

public interface FareDetailsService {

//	CompletableFuture<ResponseEntity<String>> getTravelFare(String originCode, String destinationCode);

	CompletableFuture<TripDetails> getTravelFare(String originCode, String destinationCode);

}