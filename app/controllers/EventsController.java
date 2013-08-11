package controllers;

import model.dao.EventDAO;
import model.domain.Wedding;
import model.domain.Event;
import model.domain.events.*;
import model.domain.Task;
import model.domain.tasks.*;

import views.html.events.*;
import views.html.errors.*;
import views.html.helper.budget.*;

import play.mvc.Controller;
import play.mvc.Result;
import play.api.mvc.Call;
import play.data.Form;
import play.data.DynamicForm;
import play.libs.Json;
import org.codehaus.jackson.node.ObjectNode;

public class EventsController extends WeddingController {

	public static Result show(String name) {
		Event event = currentWedding().getEvent(name);
		if (event != null) {
			/// Couldn't get request().accepts("application/json") to work
			/// Because jquery appends */*
			if (request().headers().get("X-Requested-With") != null) {
				return ok( jsonEvent(event) );
			} else {
				return ok(show.render(event));
			}
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
			// UGLY HACK: (Because play forms don't support starting from an existing object)
			/// So, we are going to Reload from db, then updateTasks (pricing and other stuffs), finally save again!
			event = EventDAO.instance.get(event.id);
			event.updateTasks();
			EventDAO.instance.save(event);

			return ok( jsonEvent(event) );
		} else {
			return notFound( e404.render() );
		}
	}

	private static <T extends Event> Form<T> formFor(T event) {
		return views.html.helper.events.package$.MODULE$.formFor(event);
	}

	private static String range(Event event) {
		return views.html.helper.budget.package$.MODULE$.range(event);
	}

	private static ObjectNode jsonEvent(Event event) {
			ObjectNode node = (ObjectNode)Json.toJson(event);
			node.put("formatted_range", range(event));

			return node;
	}

}
