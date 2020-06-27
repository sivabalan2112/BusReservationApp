package com.ticket.booking.repo;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.mongodb.client.result.UpdateResult;
import com.ticket.booking.bean.SearchRequest;
import com.ticket.booking.bean.SearchResponse;

/**
 * 
 * @author Sivabalan
 *
 */
@Repository
public class SearchRepoImpl implements SearchRepo {

	@Autowired
	@Qualifier("mogodbMongoTemplate")
	private MongoOperations mongoOps;

	@Override
	public List<SearchResponse> getAvailabilitySearch(SearchRequest request) {
		Query query = new Query();
		if (Objects.isNull(request.getReturnDate())) {
			query.addCriteria(Criteria.where("fromCity").is(request.getFromCity()).and("toCity").is(request.getToCity())
					.and("travelDate").is(request.getTravelDate()));
		} else {
			query.addCriteria(Criteria.where("fromCity").is(request.getFromCity()).and("toCity").is(request.getToCity())
					.and("travelDate").is(request.getTravelDate()).and("returnDate").is(request.getReturnDate()));
		}

		return mongoOps.find(query, SearchResponse.class);
	}

	@Override
	public long bookTicket(SearchRequest request) {
		Query query = new Query();
		long updatedCount = 0;
		query.addCriteria(Criteria.where("fromCity").is(request.getFromCity()).and("toCity").is(request.getToCity())
				.and("travelDate").is(request.getTravelDate()).and("busNumber").is(request.getBusNumber()));
		List<SearchResponse> resp = mongoOps.find(query, SearchResponse.class);
		if (!CollectionUtils.isEmpty(resp)) {
			SearchResponse res = resp.get(0);
			if (res.getSeatsAvailableDetails().get(request.getSeatNumber())) {
				res.getSeatsAvailableDetails().put(request.getSeatNumber(), false);
				Update update = new Update();
				update.set("seatsAvailableDetails", resp.get(0).getSeatsAvailableDetails());
				UpdateResult result = mongoOps.updateFirst(query, update, SearchResponse.class);
				updatedCount = result.getModifiedCount();
			}

		}
		return updatedCount;

	}

}
