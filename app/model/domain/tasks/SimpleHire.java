package model.domain.tasks;

import model.domain.Task;
import model.domain.Event;

import java.util.Random;
import java.util.Date;
import java.util.GregorianCalendar;

import com.google.code.morphia.annotations.Embedded;
import java.math.BigDecimal;

/*
	Fake task class to simplify integration with mockup tasks
*/
public class SimpleHire extends Task {

	BigDecimal lowerEstimate;
	BigDecimal upperEstimate;

	public SimpleHire() {
		super();
	}

	public SimpleHire(Event event, String name) {
		this(event, name, 5000, new GregorianCalendar(2013, 11, 21).getTime());
	}

	public SimpleHire(Event event, String name, Integer baseEstimate, Date dueDate) {
		super(name, dueDate);
		eventType = event.name.toLowerCase();
		Random random = new Random();
		lowerEstimate = new BigDecimal(random.nextInt(10) * 100 + baseEstimate);
		upperEstimate = new BigDecimal(random.nextInt(10) * 100 + lowerEstimate.intValue());
	}

	public void updatePricingEstimate(Event event) {
	}

	public String getUrl() {
		return "/tasks/" + eventType + "/" + slug;
	}

	@Override
	public BigDecimal getLowerEstimate() {
		return lowerEstimate;
	}

	@Override
	public BigDecimal getUpperEstimate() {
		return upperEstimate;
	}

}
