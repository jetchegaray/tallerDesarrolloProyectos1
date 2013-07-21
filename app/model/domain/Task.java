package model.domain;

import java.util.Date;

public abstract class Task {

	public String name;
	public Date dueDate;

	public Task() {
		this("Unnamed... :(", new Date(2013, 11, 21));
	}

	public Task(String name, Date dueDate) {
		this.name = name;
		this.dueDate = dueDate;
	}

}
