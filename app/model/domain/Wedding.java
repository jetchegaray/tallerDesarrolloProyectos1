package model.domain;

import model.domain.Event;
import model.domain.events.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;
import org.bson.types.ObjectId;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.PredicateUtils;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Transient;
import com.google.code.morphia.annotations.Embedded;

@Entity("weddings")
public class Wedding implements Budgetable {

	@Id
	ObjectId id;

	@Transient Calendar calendar;

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

	// Initial estimates
	public Integer budgetEstimate;
	public Date dateEstimate;

	public Wedding() {
		calendar = new GregorianCalendar(2014, 5, 30);
		dateEstimate = calendar.getTime();
		budgetEstimate = 100000;
	}

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

	public List<Expense> getActiveExpenses() {
		List<Expense> expenses = new ArrayList<Expense>();
		for(Event event : getEvents()) {
			expenses.addAll(event.getActiveExpenses());
		}
		return expenses;
	}

	// Amount to spend by the budgeteable
	public BigDecimal getBudget() {
		return new BigDecimal(budgetEstimate);
	}

	// Lower end for the current estimated cost
	public BigDecimal getLowerEstimate() {
		BigDecimal acum = new BigDecimal(0);
		for(Event event : getEvents()) {
			acum = acum.add(event.getLowerEstimate());
		}
		return acum;
	}

	// Upper end for the current estimated cost
	public BigDecimal getUpperEstimate() {
		BigDecimal acum = new BigDecimal(0);
		for(Event event : getEvents()) {
			acum = acum.add(event.getUpperEstimate());
		}
		return acum;
	}

	// Costs already spent
	public BigDecimal getAmountSpent() {
		BigDecimal acum = new BigDecimal(0);
		for(Event event : getEvents()) {
			acum = acum.add(event.getAmountSpent());
		}
		return acum;
	}

	// Budget minus spent
	public BigDecimal getAmountAvailable() {
		return getBudget().subtract(getAmountSpent());
	}

	// Amount that has to be paid in the future
	public BigDecimal getAmountComprised() {
		BigDecimal acum = new BigDecimal(0);
		for(Event event : getEvents()) {
			acum = acum.add(event.getAmountComprised());
		}
		return acum;
	}

	public BigDecimal getTotalCost() {
		BigDecimal acum = new BigDecimal(0);
		for(Event event : getEvents()) {
			acum = acum.add(event.getTotalCost());
		}
		return acum;
	}
}
