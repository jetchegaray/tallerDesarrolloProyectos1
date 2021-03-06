package controllers;

import model.dao.WeddingDAO;
import model.domain.Wedding;
import model.domain.Task;
import model.domain.Event;

import views.html.dashboard.*;

import play.data.Form;
import play.data.DynamicForm;

import play.mvc.Call;
import play.mvc.Controller;
import play.mvc.Http.Context.Implicit;
import play.mvc.Result;

public class DashboardController extends WeddingController {

	public static Result show() {
		return ok(show.render(currentWedding()));
	}

}
