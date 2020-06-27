package com.ticket.booking.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ticket.booking.bean.SearchRequest;
import com.ticket.booking.bean.SearchResponse;
import com.ticket.booking.repo.SearchRepo;
/**
 * 
 * @author Sivabalan
 *
 */
@Service
public class SearchService {

	@Autowired
	SearchRepo repo;

	public List<SearchResponse> getAvailability(SearchRequest req) {
		List<SearchResponse> response = repo.getAvailabilitySearch(req);
		if (!CollectionUtils.isEmpty(response)) {
			response = response.stream().sorted(Comparator.comparingDouble(SearchResponse::getPrice))
					.collect(Collectors.toList());
		}
		return response;
	}

	public String bookTicket(SearchRequest req) {
		String str = "Selected seat is not available...";
		long l = repo.bookTicket(req);
		if (l > 0) {
			str = "Your ticket booked successfully!!!";
		}
		return str;
	}

}
