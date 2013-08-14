package model.domain.events;

import model.domain.Event;
import model.domain.tasks.FakeTask;

import java.util.Date;

public class Ceremony extends Event {

	public Ceremony() {
		super("Ceremonia");
	}

	public Ceremony(Date date) {
		this();
		this.date = date;
	}

	public void updateTasks() {
	}

	public String getTypeName() {
		return EventType.CEREMONY.toString();
	}

}
