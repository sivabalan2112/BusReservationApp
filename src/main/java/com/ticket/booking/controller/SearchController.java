package com.ticket.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.booking.bean.SearchRequest;
import com.ticket.booking.bean.SearchResponse;
import com.ticket.booking.service.SearchService;

/**
 * 
 * @author Sivabalan
 *
 */
@RestController
public class SearchController {

	@Autowired
	SearchService service;

	@PostMapping("/searchAvailability")
	public ResponseEntity<List<SearchResponse>> searchBus(@RequestBody SearchRequest req) {
		List<SearchResponse> resp = service.getAvailability(req);
		return new ResponseEntity<List<SearchResponse>>(resp, HttpStatus.OK);

	}
	
	@PostMapping("/bookTicket")
	public ResponseEntity<String> bookTicket(@RequestBody SearchRequest req) {
		 String resp = service.bookTicket(req);
		return new ResponseEntity<String>(resp, HttpStatus.OK);

	}

}
