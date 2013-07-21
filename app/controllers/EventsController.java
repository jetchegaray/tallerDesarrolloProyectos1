package controllers;

import model.domain.Wedding;
import model.domain.Event;

import views.html.events.*;
import views.html.errors.*;

import play.mvc.Controller;
import play.mvc.Result;
import play.data.Form;

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
		Event event = currentWedding().getEvent(name);
		Form<? extends Event> filledForm = formFor(event).bindFromRequest();

		if(!filledForm.hasErrors()) {
			// EventDAO.update(filledForm.get());
			return ok(show.render(event));
		} else {
			return notFound(e404.render());
		}
	}

	private static <T extends Event> Form<T> formFor(T event) {
		return views.html.helper.events.package$.MODULE$.formFor(event);
	}

}
