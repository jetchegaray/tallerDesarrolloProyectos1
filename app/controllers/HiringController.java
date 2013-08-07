package controllers;


import java.util.List;

import model.dao.HiringDAO;
import model.domain.Hiring;
import model.domain.Hiring.HiringType;
import play.data.Form;
import play.mvc.Result;

public class HiringController extends WeddingController {

	public static Form<Hiring> hiringForm = Form.form(Hiring.class);

	public static Result hiring() {
		return ok(views.html.hirings.hiring.render(getHirings()));
	}
	
	public static List<Hiring> getHirings(){
		return HiringDAO.getHiringDAO().getAllHirings();
	}
	
	public static Result getHiringByType(String type){
	//	HiringDAO.getHiringDAO().getHiringByType(type.toString());
	//	return redirect(hiring(true));
		return ok("caca");
	}
	
	

//	public static Result createExpense(String expenseType) {
//		Form<Expense> filledForm = hiringForm.bindFromRequest();
//		Wedding wedding = currentWedding();
//
//		if (!filledForm.hasErrors()) {
//			if (expenseType == null) {
//				expenseType = new DynamicForm().bindFromRequest().get("type");
//			}
//
//			Budgetable budgeteable = selectBudget(wedding, expenseType);
//			Expense expense = filledForm.get();
//			budgeteable.addExpense(expense);
//			WeddingDAO.instance.save(wedding);
//
//			return redirect(parent(expenseType));
//
//		} else {
//
//			return badRequest(newExpense.render(filledForm, expenseType));
//
//		}
//	}
}
