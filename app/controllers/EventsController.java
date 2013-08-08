package controllers;

import model.dao.EventDAO;
import model.domain.Wedding;
import model.domain.Event;
import model.domain.events.*;

import views.html.events.*;
import views.html.errors.*;

import play.mvc.Controller;
import play.mvc.Result;
import play.data.Form;
import play.data.DynamicForm;

public class EventsController extends WeddingController {

	public static Result show(String name) {
		Event event = currentWedding().getEvent(name);
		if (event != null) {
			return ok(show.render(event));
		} else {
			return notFound(e404.render());
		}
	}

	public static Result update(String name) {
		Wedding wedding = currentWedding();
		Event event     = wedding.getEvent(name);

		Form<? extends Event> filledForm = formFor(event).bindFromRequest();

		if(!filledForm.hasErrors()) {
			Event eventFromForm = filledForm.get();
			eventFromForm.id = event.id;
			EventDAO.instance.merge(eventFromForm);
			return ok(show.render(event));
		} else {
			return notFound(e404.render());
		}
	}

	private static <T extends Event> Form<T> formFor(T event) {
		return views.html.helper.events.package$.MODULE$.formFor(event);
	}

}
