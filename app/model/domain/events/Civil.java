package model.domain.events;

import model.domain.Event;
import model.domain.tasks.FakeTask;

public class Civil extends Event {

	public Civil() {
		super("Civil");
		tasks.add(new FakeTask("Reservar registro civil", "101"));
		tasks.add(new FakeTask("Enviar invitaciones", "102"));
	}

}
