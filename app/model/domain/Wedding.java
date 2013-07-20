package model.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import model.domain.events.*;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.PredicateUtils;

public class Wedding {

	public List<Guest> husbandGuests;
	public List<Guest> wifeGuests;
	public List<Present> presents;
	public List<Event> events;

	public Place place;

	Calendar calendar;
	Date date;

	public Event getEvent(String type) {
		Class<? extends Event> eventType = Event.getType(type);

		return (Event)CollectionUtils.find(events, PredicateUtils.instanceofPredicate(eventType));
	}

	public Wedding() {
		events = new ArrayList<Event>();
		events.add(new Civil());
		events.add(new Ceremony());
		events.add(new Party());
	}

}
