package controllers;

import model.dao.WeddingDAO;
import model.dao.EventDAO;
import model.domain.Wedding;
import model.domain.Event;
import model.domain.Expense;
import model.domain.Budgetable;

import views.html.budget.*;
import views.html.errors.*;

import play.data.Form;
import play.data.DynamicForm;

import java.math.BigDecimal;
import play.mvc.Call;
import play.mvc.Controller;
import play.mvc.Http.Context.Implicit;
import play.mvc.Result;

public class BudgetController extends WeddingController {

	public static Form<Expense> expenseForm = Form.form(Expense.class);

	public static Result show() {
		return ok( show.render(currentWedding()) );
	}

	public static Result newExpense(String expenseType) {
		return ok(newExpense.render(expenseForm, expenseType));
	}

	public static Result createExpense(String expenseType) {
		Form<Expense> filledForm = expenseForm.bindFromRequest();
		Wedding wedding = currentWedding();

		if (!filledForm.hasErrors()) {
			if (expenseType == null) {
				expenseType = new DynamicForm().bindFromRequest().get("type");
			}
			Expense expense = filledForm.get();

			if (expenseType != null && !expenseType.isEmpty()) {
				Event event = wedding.getEvent(expenseType);
				event.addExpense(expense);
				EventDAO.instance.save(event);
			} else {
				wedding.addExpense(expense);
				WeddingDAO.instance.save(wedding);
			}

			return redirect(parent(expenseType));

		} else {

			return badRequest(newExpense.render(filledForm, expenseType));

		}
	}

	public static Call parent(String expenseType) {
		if (expenseType != null && !expenseType.isEmpty()) {
			return routes.EventsController.show(expenseType);
		} else {
			return routes.BudgetController.show();
		}
	}

}
