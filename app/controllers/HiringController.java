package controllers;


import java.util.List;
import java.util.Map;

import model.dao.HiringDAO;
import model.domain.Hiring;
import play.data.Form;
import play.libs.Json;
import play.mvc.Result;

import com.google.common.collect.Maps;

public class HiringController extends WeddingController {

	public static Form<Hiring> hiringForm = Form.form(Hiring.class);

	public static Result hiring() {
		return ok(views.html.hirings.hiring.render(getHirings()));
	}
	
	public static List<Hiring> getHirings(){
		return HiringDAO.getHiringDAO().getAllHirings();
	}
	
	public static Result getHiringByType(String type){
		List<Hiring> hirings = HiringDAO.getHiringDAO().getHiringByType(type);
		Map<String, List<Hiring>> mapa = Maps.<String, List<Hiring>>newHashMap();
		mapa.put("hirings", hirings);
		return ok(Json.toJson(mapa));
	}

	
	//FIXME cualquiera ¡¡¡
	public static Result getConversation(String name){
		Hiring hiring = HiringDAO.getHiringDAO().findOne("name",name);
	//	hiringForm.fill(hiring).get().getName()
		return ok(views.html.hirings.conversation.render(hiring));
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
