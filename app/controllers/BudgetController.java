package controllers;

import model.dao.WeddingDAO;
import model.domain.Wedding;
import model.domain.Expense;
import model.domain.Budgetable;

import views.html.budget.*;
import views.html.errors.*;

import play.data.Form;
import play.data.DynamicForm;

import play.mvc.Call;
import play.mvc.Controller;
import play.mvc.Http.Context.Implicit;
import play.mvc.Result;

public class BudgetController extends WeddingController {

	public static Form<Expense> expenseForm = Form.form(Expense.class);

	public static Result show() {
		return ok(show.render(currentWedding()));
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

			Budgetable budgeteable = selectBudget(wedding, expenseType);
			Expense expense = filledForm.get();
			budgeteable.addExpense(expense);
			WeddingDAO.instance.save(wedding);

			return redirect(parent(expenseType));

		} else {

			return badRequest(newExpense.render(filledForm, expenseType));

		}
	}

	public static Call parent(String expenseType) {
		if (expenseType != null) {
			return routes.EventsController.show(expenseType);
		} else {
			return routes.BudgetController.show();
		}
	}

	public static Budgetable selectBudget(Wedding wedding, String expenseType) {
		if (expenseType != null && !expenseType.isEmpty()) {
			return wedding.getEvent(expenseType);
		} else {
			return wedding;
		}
	}
}
