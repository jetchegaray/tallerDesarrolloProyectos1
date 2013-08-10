package controllers;

import model.dao.WeddingDAO;
import model.domain.Wedding;
import model.domain.Event;
import model.domain.Task;

import views.html.tasks.*;
import views.html.errors.*;

import play.data.Form;
import play.data.DynamicForm;

import play.mvc.Call;
import play.mvc.Controller;
import play.mvc.Http.Context.Implicit;
import play.mvc.Result;

public class TasksController extends WeddingController {

	public static Result show(String eventType, String slug) {
		Wedding wedding = currentWedding();
		Event event = wedding.getEvent(eventType);
		Task  task  = event.findTaskBySlug(slug);
		return ok(show.render(event, task));
	}

	public static Result update(String eventType, String slug) {
		Wedding wedding = currentWedding();
		Event event = wedding.getEvent(eventType);
		Task  task  = event.findTaskBySlug(slug);
		return ok(show.render(event, task));
	}

}
