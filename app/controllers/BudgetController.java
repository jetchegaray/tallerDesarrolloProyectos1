package controllers;

import model.domain.Wedding;
import model.domain.Event;

import views.html.budget.*;
import views.html.errors.*;

import play.mvc.Controller;
import play.mvc.Result;
import play.data.Form;

public class BudgetController extends WeddingController {

	public static Result show() {
		return ok(show.render(currentWedding()));
	}

}
