package com.afkl.interview.fareinfo.common;

import static org.springframework.hateoas.PagedResources.wrap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.PagedResources.PageMetadata;
import org.springframework.hateoas.Resource;

import com.afkl.interview.fareinfo.exceptions.TravelApiException;
import com.google.common.collect.Lists;

public class Pageable<T> {

	private final int page, size;

	public Pageable(int page, int size) {
		this.page = page;
		this.size = size;
	}

	public int getPage() {
		return page;
	}

	public int getSize() {
		return size;
	}

	public PagedResources<Resource<T>> partition(Collection<T> original) {
		if (original == null || original.isEmpty()) {
			throw new TravelApiException("No Records Found");
		}
		final List<List<T>> partitionedList = Lists
				.partition(original instanceof List ? (List) original : new ArrayList<>(original), size);
		try {
			List<T> result = partitionedList.get(page == 0 ? page : page - 1);
			final PageMetadata metadata = new PageMetadata(size, page, original.size(), partitionedList.size());
			return wrap(result, metadata);
		} catch (IndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Requested page is out of bounds.");
		}
	}

}
