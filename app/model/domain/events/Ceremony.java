package model.domain.events;

import model.domain.Event;
import model.domain.tasks.FakeTask;

public class Ceremony extends Event {

	public Ceremony() {
		super("Ceremonia");
		tasks.add(new FakeTask("Reservar templo", "201"));
		tasks.add(new FakeTask("Comprar anillos", "hire"));
		tasks.add(new FakeTask("Comprar traje del novio", "hire"));
		tasks.add(new FakeTask("Comprar vestido de novia", "hire-dress"));
		tasks.add(new FakeTask("Enviar invitaciones", "102"));
	}

}
