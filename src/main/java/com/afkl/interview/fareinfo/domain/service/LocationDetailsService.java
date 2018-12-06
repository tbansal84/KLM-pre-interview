package com.afkl.interview.fareinfo.domain.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;

import com.afkl.interview.fareinfo.common.Pageable;
import com.afkl.interview.fareinfo.dtos.locations.Location;

public interface LocationDetailsService {

	CompletableFuture<Location> getLocationDetails(String location);

	List<Location> getLocationsList(String searchcriteria);

	PagedResources<Resource<Location>> getLocationsList(Pageable<Location> pagable, String sortBy);

	List<Location> getSortedLocationsList(String sortBy);

}