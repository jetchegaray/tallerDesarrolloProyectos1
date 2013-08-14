package model.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

import com.google.code.morphia.annotations.Embedded;
import java.util.Date;

public abstract class Task {

	public String name;
	public String slug;
	public Date dueDate;
	public boolean done = false;
	public String eventType;

	public Task() { }

	public Task(String name, Date dueDate) {
		this.name = name;
		this.slug = name.replaceAll("\\W", "-").toLowerCase();
		this.dueDate = dueDate;
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

	public void completeTask(){
		this.done = true;
	}

	public abstract void updatePricingEstimate(Event event);

}
