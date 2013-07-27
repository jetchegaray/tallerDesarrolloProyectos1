package model.domain.events;

import model.domain.Event;
import model.domain.tasks.FakeTask;

public class Party extends Event {

	// public

	public Party() {
		super("Fiesta");
	}

	private enum Style {
		DAY, NIGHT
	}
}
