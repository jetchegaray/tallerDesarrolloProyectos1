package model.domain;

import java.util.Date;
import java.util.GregorianCalendar;

import com.google.code.morphia.annotations.Embedded;

public abstract class Task {

	public String name;
	public Date dueDate;

	public Task() {
		this("Unnamed... :(", new GregorianCalendar(2013, 11, 21).getTime());
	}

	public Task(String name, Date dueDate) {
		this.name = name;
		this.dueDate = dueDate;
	}

}
