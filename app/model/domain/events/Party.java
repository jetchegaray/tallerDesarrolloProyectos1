package model.domain.events;

import model.domain.Event;
import model.domain.tasks.FakeTask;

public class Party extends Event {

	public Party() {
		super("Fiesta");
		// 202 are /mockup/hire tasks
		tasks.add(new FakeTask("Contratar Salón", "hire"));
		tasks.add(new FakeTask("Contratar Catering", "hire"));
		tasks.add(new FakeTask("Enviar invitaciones", "102"));
	}

}
