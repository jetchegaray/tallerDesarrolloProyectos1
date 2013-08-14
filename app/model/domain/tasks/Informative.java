package model.domain.tasks;

import model.domain.Task;
import model.domain.Event;

import java.util.Random;
import java.util.Date;
import java.util.GregorianCalendar;

import com.google.code.morphia.annotations.Embedded;
import java.math.BigDecimal;

/*
	Just information of something that should be done (without cost)
*/
public class Informative extends Task {

	public Informative() { super(); }

	public Informative(String name, Date dueDate) {
		super(name, dueDate);
	}

	public void updatePricingEstimate(Event event) { }

}
