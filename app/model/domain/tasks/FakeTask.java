package model.domain.tasks;

import model.domain.Task;

import java.util.Date;

/*
	Fake task class to simplify integration with mockup tasks
*/
public class FakeTask extends Task {

	public String mockupId;

	public FakeTask(String name, String id) {
		super(name, new Date(2013, 11, 21));
		this.mockupId = id;
	}

	public String target() {
		if (mockupId.equals("hire")) {
			return "202";
		} else {
			return mockupId;
		}
	}

}
