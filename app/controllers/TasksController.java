package controllers;

import model.dao.EventDAO;
import model.domain.Wedding;
import model.domain.Event;
import model.domain.Expense;
import model.domain.Expense.CustomHire;
import model.domain.Task;

import views.html.tasks.*;
import views.html.errors.*;

import play.data.Form;
import play.data.DynamicForm;

import java.math.BigDecimal;
import java.util.Map;
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
		DynamicForm params = new DynamicForm().bindFromRequest();
		if (params.get("particular_hire") != null) {
			return createPersonalHire(wedding, event, task, params.data());
		}
		return redirect( routes.TasksController.show(eventType, slug) );
	}

	private static Result createPersonalHire(Wedding wedding, Event event, Task task, Map<String, String> data) {
		String ammount = data.get("expense[total]");
		CustomHire expense = new CustomHire(task, new BigDecimal(ammount));
		expense.name = data.get("hire[name]");
		expense.email = data.get("hire[email]");
		expense.description = data.get("hire[description]");

		event.addExpense(expense);
		task.complete();

		EventDAO.instance.save(event);
		return redirect( routes.EventsController.show(event.getTypeName()) );
	}

}
