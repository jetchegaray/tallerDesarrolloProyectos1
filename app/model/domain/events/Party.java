package model.domain.events;

import model.domain.Event;
import model.domain.tasks.FakeTask;

public class Party extends Event {

	public String city;

	public Party() {
		super("Fiesta");
	}

	public String getTypeName() {
		return EventType.PARTY.toString();
	}

	private enum Style {
		DAY, NIGHT
	}
}
