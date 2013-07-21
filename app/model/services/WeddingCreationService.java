package model.services;

import model.domain.Wedding;
import model.domain.events.*;
import model.domain.tasks.*;

import model.dao.WeddingDAO;

public class WeddingCreationService {

	public Wedding createWedding(Wedding wedding) {

		wedding.civil    = initializeCivil();
		wedding.ceremony = initializeCeremony();
		wedding.party    = initializeParty();

		WeddingDAO.instance.save(wedding);

		return wedding;
	}

	private Civil initializeCivil() {
		Civil civil = new Civil();
		civil.addTask(new FakeTask("Reservar registro civil", "101"));
		civil.addTask(new FakeTask("Enviar invitaciones", "102"));
		return civil;
	}

	private Ceremony initializeCeremony() {
		Ceremony ceremony = new Ceremony();
		ceremony.addTask(new FakeTask("Reservar templo", "201"));
		ceremony.addTask(new FakeTask("Comprar anillos", "hire"));
		ceremony.addTask(new FakeTask("Comprar traje del novio", "hire"));
		ceremony.addTask(new FakeTask("Comprar vestido de novia", "hire-dress"));
		ceremony.addTask(new FakeTask("Enviar invitaciones", "102"));
		return ceremony;
	}

	private Party initializeParty() {
		Party party = new Party();
		party.addTask(new FakeTask("Contratar Salón", "hire"));
		party.addTask(new FakeTask("Contratar Catering", "hire"));
		party.addTask(new FakeTask("Enviar invitaciones", "102"));
		return party;
	}
}
