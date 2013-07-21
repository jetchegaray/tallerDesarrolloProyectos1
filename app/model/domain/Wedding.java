package model.domain;

import model.domain.Event;
import model.domain.events.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.bson.types.ObjectId;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.PredicateUtils;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Transient;
import com.google.code.morphia.annotations.Embedded;

@Entity("weddings")
public class Wedding {

	@Id
	ObjectId id;

	@Transient Calendar calendar;
	@Transient Date date;

	@Transient
	public List<Guest> husbandGuests;
	@Transient
	public List<Guest> wifeGuests;
	@Transient
	public List<Present> presents;

	@Transient
	public Place place;

	@Transient private List<Event> events;
	@Embedded public Civil civil;
	@Embedded public Ceremony ceremony;
	@Embedded public Party party;

	public String getId() {
		return id.toString();
	}

	public Event getEvent(String type) {
		Class<? extends Event> eventType = Event.getType(type);

		return (Event)CollectionUtils.find(getEvents(), PredicateUtils.instanceofPredicate(eventType));
	}

	public List<Event> getEvents() {
		if (events != null) return events;

		events = new ArrayList<Event>();

		if (civil != null)    events.add(civil);
		if (ceremony != null) events.add(ceremony);
		if (party != null)    events.add(party);

		return events;
	}

}
