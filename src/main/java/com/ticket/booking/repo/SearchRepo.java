package com.ticket.booking.repo;

import java.util.List;

import com.ticket.booking.bean.SearchRequest;
import com.ticket.booking.bean.SearchResponse;

/**
 * 
 * @author Sivabalan
 *
 */
public interface SearchRepo {

	List<SearchResponse> getAvailabilitySearch(SearchRequest request);

	long bookTicket(SearchRequest req);
}
