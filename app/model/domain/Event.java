package model.domain;

import model.domain.events.*;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.bson.types.ObjectId;
import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

public abstract class Event {

	public String name;
	public Date date;
	public Integer guestEstimate;
	@Embedded List<Task> tasks;

	public String city;

	public Event() {
		this("Unnamed... :(");
	}

	public Event(String name) {
		this.name = name;
		this.tasks = new ArrayList<Task>();
	}

	public void addTask(Task t) {
		tasks.add(t);
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
