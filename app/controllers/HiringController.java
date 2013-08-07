package controllers;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import model.dao.HiringDAO;
import model.domain.Hiring;
import model.domain.Hiring.HiringType;
import play.data.Form;
import play.libs.Json;
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
		Hiring hiring = new Hiring();
		hiring.setName("La Parada");
		hiring.setAddress("Rivadavia 1598");
		hiring.setPrice(BigDecimal.valueOf(123));
		hiring.setType(HiringType.PLACE);
		hiring.setMail("Laparada25@fibertel.com.ar");
		hiring.setPicture("http://lorempixel.com/64/64/people");
		
		hiring.addConversationMessageUser("Hola, queria saber si el lugar estaria disponible para la fecha 12/21/2014");
		hiring.addConversationMessageHireUser("Si esta disponible");
		hiring.addConversationMessageUser("bueno , gracias, el precio es el publicado ?");
		hiring.addConversationMessageHireUser("exacto.. cualquier cosa reserva con tiempo, porque estamos reservando con muco tiempo de anticipacion");
		hiring.addConversationMessageUser("Gracias ¡ te agradezco..");
		
		Hiring hiring2 = new Hiring();
		hiring2.setName("La Parada");
		hiring2.setAddress("Rivadavia 1598");
		hiring2.setPrice(BigDecimal.valueOf(123));
		hiring2.setType(HiringType.PLACE);
		hiring2.setMail("Laparada25@fibertel.com.ar");
		
		hiring2.addConversationMessageUser("Hola, queria saber si el lugar estaria disponible para la fecha 12/21/2014");
		hiring2.addConversationMessageHireUser("Si esta disponible");
		hiring2.addConversationMessageUser("bueno , gracias, el precio es el publicado ?");
		hiring2.addConversationMessageHireUser("exacto.. cualquier cosa reserva con tiempo, porque estamos reservando con muco tiempo de anticipacion");
		hiring2.addConversationMessageUser("Gracias ¡ te agradezco..");
		
		Map<String, List<Hiring>> mapa = Maps.<String, List<Hiring>>newHashMap();
		mapa.put("hirings", Arrays.asList(hiring));
		return ok(Json.toJson(mapa));

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
