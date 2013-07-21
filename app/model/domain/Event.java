package model.domain;

import model.domain.events.*;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public abstract class Event {

	public String name;
	public String city;
	public Date date;
	public List<Task> tasks;

	public Event() {
		this("Unnamed... :(");
	}

	public Event(String name) {
		this.name = name;
		this.tasks = new ArrayList<Task>();
	}

	public List<Task> pendingTasks() {
		return tasks;
	}

	public static Class<? extends Event> getType(String str) {
		EventType type = EventType.valueOf(str);

		switch (type) {
			case CIVIL:    return Civil.class;
			case CEREMONY: return Ceremony.class;
			case PARTY:    return Party.class;
			default: throw new RuntimeException("Unknown event type: " + str);
		}
	}

	private enum EventType {
		CIVIL, CEREMONY, PARTY
	}

}