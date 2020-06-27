package com.ticket.booking.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Sivabalan
 *
 */
@Getter
@Setter
public class SearchRequest {

	private String fromCity;
	private String toCity;
	private String travelDate;
	private String returnDate;
	private String busNumber;
	private String seatNumber;

}
