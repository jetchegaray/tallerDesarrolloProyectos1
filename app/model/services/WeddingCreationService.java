package model.services;

import model.domain.Wedding;
import model.domain.events.*;
import model.domain.tasks.*;

import model.dao.WeddingDAO;
import model.dao.EventDAO;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class WeddingCreationService {

	public Wedding createWedding(Wedding wedding) {

		wedding.civil    = initializeCivil(wedding);
		wedding.ceremony = initializeCeremony(wedding);
		wedding.party    = initializeParty(wedding);

		WeddingDAO.instance.save(wedding);

		return wedding;
	}

	private Civil initializeCivil(Wedding wedding) {
		Civil civil = new Civil();
		civil.addTask(new FakeTask("Preparar documentación", "102", daysBefore(wedding, 30 * 6)));
		civil.addTask(new FakeTask("Elegir testigos", "102", daysBefore(wedding, 30 * 4)));
		civil.addTask(new FakeTask("Reservar registro civil", "101", daysBefore(wedding, 30 * 1)));
		civil.addTask(new FakeTask("Definir lista de invitados", "102", daysBefore(wedding, 30 * 3)));
		// This task should be included when the previous task is completed
		civil.addTask(new FakeTask("Enviar invitaciones", "102", daysBefore(wedding, 30 * 2)));
		// This tasks should be included when this event changes to "withReception = true"
		civil.addTask(new FakeTask("Comprar bebidas y comida para la recepción", "101", daysBefore(wedding, 30* 3)));

		EventDAO.instance.save(civil);
		return civil;
	}

	private Ceremony initializeCeremony(Wedding wedding) {
		Ceremony ceremony = new Ceremony();
		ceremony.addTask(new FakeTask("Reservar templo", "201", daysBefore(wedding, 30 * 9)));
		ceremony.addTask(new FakeTask("Preparar documentación", "102", daysBefore(wedding, 30 * 6)));
		ceremony.addTask(new FakeTask("Elegir padrinos", "102", daysBefore(wedding, 30 * 4)));
		ceremony.addTask(new FakeTask("Concurrir a pláticas prematrimoniales", "201", daysBefore(wedding, 30 * 3)));
		ceremony.addTask(new FakeTask("Comprar alianzas", "hire", daysBefore(wedding, 30 * 3)));
		ceremony.addTask(new FakeTask("Definir lista de invitados", "102", daysBefore(wedding, 30 * 3)));
		// This task should be included when the previous task is completed
		ceremony.addTask(new FakeTask("Enviar invitaciones", "102", daysBefore(wedding, 30 * 2)));

		EventDAO.instance.save(ceremony);
		return ceremony;
	}

	private Party initializeParty(Wedding wedding) {
		Party party = new Party();
		party.addTask(new FakeTask("Contratar Salón", "hire", daysBefore(wedding, 30 * 8)));
		party.addTask(new FakeTask("Contratar Catering", "hire", daysBefore(wedding, 30 * 7)));
		party.addTask(new FakeTask("Contratar Peluqueria", "hire", daysBefore(wedding, 30 * 3)));
		party.addTask(new FakeTask("Contratar Florista", "hire", daysBefore(wedding, 30 * 6)));
		party.addTask(new FakeTask("Contratar impresión de invitaciones", "hire", daysBefore(wedding, 30 * 4)));
		party.addTask(new FakeTask("Comprar traje del novio", "hire", daysBefore(wedding, 30 * 4)));
		party.addTask(new FakeTask("Comprar vestido de novia", "hire-dress", daysBefore(wedding, 30 * 6)));
		party.addTask(new FakeTask("Comprar souvenires", "hire", daysBefore(wedding, 30 * 3)));
		party.addTask(new FakeTask("Elegir casa de regalos y armar la lista", "hire", daysBefore(wedding, 30 * 2)));
		party.addTask(new FakeTask("Definir lista de invitados", "102", daysBefore(wedding, 30 * 3)));
		party.addTask(new FakeTask("Reservar hotel para noche de bodas", "hire", daysBefore(wedding, 30 * 2)));
		// This task should be included when the previous task is completed
		party.addTask(new FakeTask("Enviar invitaciones", "102", daysBefore(wedding, 30 * 2)));

		// This task should be included after the task "contratar salon" Currently is hardcoded in
		// the view: showParty
		// party.addTask(new FakeTask("Organizar mesas", "102", daysBefore(wedding, 30 * 2)));
		EventDAO.instance.save(party);
		return party;
	}

	private Date daysBefore(Wedding wedding, Integer count) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setGregorianChange(wedding.dateEstimate);
		calendar.add(Calendar.DATE, -count);
		return calendar.getTime();
	}

}
