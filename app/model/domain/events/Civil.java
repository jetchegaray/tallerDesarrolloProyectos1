package model.domain.events;

import model.domain.Event;
import model.domain.tasks.FakeTask;
import com.google.code.morphia.annotations.Embedded;

import java.util.Date;

public class Civil extends Event {

	public Boolean withReception;

	public Civil() {
		super("Civil");
	}

	public Civil(Date date) {
		this();
		this.date = date;
	}


	public void updateTasks() {
	}

	public String getTypeName() {
		return EventType.CIVIL.toString();
	}
}
