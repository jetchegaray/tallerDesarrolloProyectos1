package model.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

import com.google.code.morphia.annotations.Embedded;
import java.util.Date;

public abstract class Task implements Costable {

	public String name;
	public String slug;
	public Date dueDate;
	public Boolean done;
	public String eventType;

	protected Task() {
		this.done = false;
	}

	public Task(String name, Date dueDate) {
		this.name = name;
		this.slug = name.replaceAll("\\W", "-").toLowerCase();
		this.dueDate = dueDate;
	}

	// Lower end for the current estimated cost
	public BigDecimal getLowerEstimate() {
		return new BigDecimal(0);
	}

	// Upper end for the current estimated cost
	public BigDecimal getUpperEstimate() {
		return new BigDecimal(0);
	}

	public void complete() {
		this.done = true;
	}

	public boolean isPending() {
		return !done;
	}

	public String getUrl() {
		return "/tasks/" + eventType + "/" + slug;
	}

	public abstract void updatePricingEstimate(Event event);

}
