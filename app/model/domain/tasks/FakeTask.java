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
public class FakeTask extends Task {

	public String mockupId;
	BigDecimal lowerEstimate;
	BigDecimal upperEstimate;

	public FakeTask() {
		super();
	}

	public FakeTask(String name, String id) {
		this(name, id, new GregorianCalendar(2013, 11, 21).getTime());
	}

	public FakeTask(String name, String id, Date dueDate) {
		this(name, id, dueDate, 0);
	}

	public FakeTask(String name, String id, Date dueDate, Integer base) {
		super(name, dueDate);
		this.mockupId = id;
		Random random = new Random();
		lowerEstimate = new BigDecimal(base);
		upperEstimate = new BigDecimal((random.nextInt(4) + 2) * base);
	}

	public void updatePricingEstimate(Event event) {
		if (slug.equals("contratar-sal-n")) {
			lowerEstimate = lowerEstimate.add(new BigDecimal(1000));
			upperEstimate = upperEstimate.add(new BigDecimal(1000));
		}
	}

	@Override
	public String getUrl() {
		return "/mockup/tasks/" + mockupId;
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
