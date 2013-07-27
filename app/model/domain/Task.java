package model.domain;

import java.util.Date;
import java.util.GregorianCalendar;

import com.google.code.morphia.annotations.Embedded;
import java.util.Date;

public abstract class Task {

	public String name;
	public Date dueDate;

	protected Task() {}

	public Task(String name, Date dueDate) {
		this.name = name;
		this.dueDate = dueDate;
	}

}
