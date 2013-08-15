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
		Civil civil = new Civil(wedding.dateEstimate);
		civil.addTask(new Informative("Preparar documentación", daysBefore(wedding, 30 * 6)));
		civil.addTask(new FakeTask("Elegir testigos", "102", daysBefore(wedding, 30 * 4)));
		civil.addTask(new FakeTask("Reservar registro civil", "101", daysBefore(wedding, 30 * 1)));
		// civil.addTask(new FakeTask("Definir lista de invitados", "102", daysBefore(wedding, 30 * 3)));
		// This task should be included when the previous task is completed
		// civil.addTask(new FakeTask("Enviar invitaciones", "102", daysBefore(wedding, 30 * 2)));
		// This tasks should be included when this event changes to "withReception = true"
		civil.addTask(new SimpleHire(civil, "Comprar bebidas y comida para la recepción", 3500, daysBefore(wedding, 30* 3)));

		EventDAO.instance.save(civil);
		return civil;
	}

	private Ceremony initializeCeremony(Wedding wedding) {
		Ceremony ceremony = new Ceremony(wedding.dateEstimate);
		ceremony.addTask(new FakeTask("Reservar templo", "201", daysBefore(wedding, 30 * 9)));
		ceremony.addTask(new Informative("Preparar documentación", daysBefore(wedding, 30 * 6)));
		ceremony.addTask(new FakeTask("Elegir padrinos", "102", daysBefore(wedding, 30 * 4)));
		ceremony.addTask(new Informative("Concurrir a pláticas prematrimoniales", daysBefore(wedding, 30 * 3)));
		ceremony.addTask(new SimpleHire(ceremony, "Comprar alianzas", 2500, daysBefore(wedding, 30* 3)));
		// ceremony.addTask(new FakeTask("Definir lista de invitados", "102", daysBefore(wedding, 30 * 3)));
		// This task should be included when the previous task is completed
		// ceremony.addTask(new FakeTask("Enviar invitaciones", "102", daysBefore(wedding, 30 * 2)));

		EventDAO.instance.save(ceremony);
		return ceremony;
	}

	private Party initializeParty(Wedding wedding) {
		Party party = new Party(wedding.dateEstimate);
		party.addTask(new HireProvider(party, "Contratar Salón", 35000, daysBefore(wedding, 30 * 8), "Lugares"));
		party.addTask(new SimpleHire(party, "Contratar Catering", 5000, daysBefore(wedding, 30 * 7)));
		party.addTask(new SimpleHire(party, "Contratar Peluqueria", 500, daysBefore(wedding, 30 * 3)));
		party.addTask(new SimpleHire(party, "Contratar Florista", 1000, daysBefore(wedding, 30 * 6)));
		party.addTask(new SimpleHire(party, "Contratar impresión de invitaciones", 750, daysBefore(wedding, 30 * 4)));
		party.addTask(new SimpleHire(party, "Comprar traje del novio", 750, daysBefore(wedding, 30 * 4)));
		party.addTask(new FakeTask("Comprar vestido de novia", "hire-dress", daysBefore(wedding, 30 * 6), 2500));
		party.addTask(new SimpleHire(party, "Comprar souvenires", 1000, daysBefore(wedding, 30 * 3)));
		party.addTask(new Informative("Elegir casa de regalos y armar la lista", daysBefore(wedding, 30 * 2)));
		// party.addTask(new FakeTask("Definir lista de invitados", "102", daysBefore(wedding, 30 * 3)));
		party.addTask(new SimpleHire(party, "Reservar hotel para noche de bodas", 500, daysBefore(wedding, 30 * 2)));
		// This task should be included when the previous task is completed
		// party.addTask(new FakeTask("Enviar invitaciones", "102", daysBefore(wedding, 30 * 2)));

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
