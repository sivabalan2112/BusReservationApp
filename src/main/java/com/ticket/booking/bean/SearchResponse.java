package com.ticket.booking.bean;

import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Sivabalan
 *
 */
@Getter
@Setter
@Document(collection = "busDetails")
public class SearchResponse {

	private String busNumber;
	private String operatorName;
	private String departure;
	private String arrivalTime;
	private String duration;
	private double price;
	private Map<String, Boolean> seatsAvailableDetails;
}
