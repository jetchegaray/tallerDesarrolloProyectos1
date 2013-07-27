package model.domain.tasks;

import model.domain.Task;

import java.util.Date;
import java.util.GregorianCalendar;

import com.google.code.morphia.annotations.Embedded;
/*
	Fake task class to simplify integration with mockup tasks
*/
public class FakeTask extends Task {

	public String mockupId;

	public FakeTask() {
		super();
	}

	public FakeTask(String name, String id) {
		this(name, id, new GregorianCalendar(2013, 11, 21).getTime());
	}

	public FakeTask(String name, String id, Date dueDate) {
		super(name, dueDate);
		this.mockupId = id;
	}

	public String target() {
		if (mockupId.equals("hire")) {
			return "202";
		} else if (mockupId.equals("hire-dress")) {
			return "204";
		} else {
			return mockupId;
		}
	}

}
