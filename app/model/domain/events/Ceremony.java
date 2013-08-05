package model.domain.events;

import model.domain.Event;
import model.domain.tasks.FakeTask;

public class Ceremony extends Event {

	public Ceremony() {
		super("Ceremonia");
	}

	public String getTypeName() {
		return EventType.CEREMONY.toString();
	}

}
