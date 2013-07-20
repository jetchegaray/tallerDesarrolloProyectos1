package controllers;

import model.domain.Wedding;
import model.domain.Event;

import views.html.events.*;

import play.mvc.Controller;
import play.mvc.Result;

public class EventsController extends Controller {

	public static Result show(String name) {
		Event event = new Wedding().getEvent(name);
		if (event != null) {
			return ok(show.render(event));
		} else {
			return notFound("Sorry");
		}
	}

}
