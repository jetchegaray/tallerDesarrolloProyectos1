package model.domain.events;

import model.domain.Event;
import model.domain.tasks.FakeTask;

public class Party extends Event {

	public Party() {
		super("Fiesta");
	}

}
