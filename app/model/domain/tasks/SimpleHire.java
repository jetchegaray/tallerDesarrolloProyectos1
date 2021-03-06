package model.domain.tasks;

import model.domain.Costable;
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
public class SimpleHire extends Task implements Costable {

	BigDecimal lowerEstimate;
	BigDecimal upperEstimate;

	public SimpleHire() {
		super();
	}

	public SimpleHire(Event event, String name) {
		this(event, name, 5000);
	}

	public SimpleHire(Event event, String name, Integer baseEstimate) {
		this(event, name, baseEstimate, new GregorianCalendar(2013, 11, 21).getTime());
	}

	public SimpleHire(Event event, String name, Integer baseEstimate, Date dueDate) {
		super(name, dueDate);
		Random random = new Random();
		lowerEstimate = new BigDecimal(random.nextInt(10) * 100 + baseEstimate);
		upperEstimate = new BigDecimal((random.nextInt(9) + 1) * 100 + lowerEstimate.intValue());
	}

	public void updatePricingEstimate(Event event) { }

	@Override
	public BigDecimal getLowerEstimate() {
		return lowerEstimate;
	}

	@Override
	public BigDecimal getUpperEstimate() {
		return upperEstimate;
	}

}
