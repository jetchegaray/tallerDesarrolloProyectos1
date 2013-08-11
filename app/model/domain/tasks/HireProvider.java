package model.domain.tasks;

import model.domain.Costable;
import model.domain.Task;
import model.domain.events.Party;
import model.domain.Event;

import java.util.Random;
import java.util.Date;
import java.util.GregorianCalendar;

import com.google.code.morphia.annotations.Embedded;
import java.math.BigDecimal;

/*
	Fake task class to simplify integration with mockup tasks
*/
public class HireProvider extends Task implements Costable {

	BigDecimal lowerEstimate;
	BigDecimal upperEstimate;
	Integer baseEstimate;
	String providerType;

	public HireProvider() {
		super();
	}

	public HireProvider(Event event, String name) {
		this(event, name, 5000);
	}

	public HireProvider(Event event, String name, Integer baseEstimate) {
		this(event, name, baseEstimate, new GregorianCalendar(2013, 11, 21).getTime(), "Lugares");
	}

	public HireProvider(Event event, String name, Integer baseEstimate, Date dueDate, String providerType) {
		super(name, dueDate);
		eventType = event.name.toLowerCase();
		Random random = new Random();
		this.baseEstimate = baseEstimate;
		this.providerType = providerType;
		updatePricingEstimate(event);
	}

	public void updatePricingEstimate(Event event) {
		if (slug.equals("contratar-sal-n")) {
			calculateSalonEstimate((Party)event);
		} else {
			lowerEstimate = new BigDecimal(baseEstimate);
			upperEstimate = new BigDecimal(baseEstimate * 1.8);
		}
	}

	@Override
	public BigDecimal getLowerEstimate() {
		return lowerEstimate;
	}

	@Override
	public BigDecimal getUpperEstimate() {
		return upperEstimate;
	}

	private void calculateSalonEstimate(Party party) {
		Integer acum = 0;

		if ("NIGHT".equals(party.when)) {
			acum += 2500;
		}

		if ("Capital Federal".equals(party.city)) {
			acum += 1500;
		}
		if ("Pilar".equals(party.city)) {
			acum += 3000;
		}

		lowerEstimate = new BigDecimal(baseEstimate + acum);
		upperEstimate = new BigDecimal((baseEstimate + acum) * 1.8);
	}

}
