package controllers;


import java.util.List;

import model.dao.HiringDAO;
import model.domain.Hiring;
import play.data.Form;
import play.mvc.Result;

public class HiringController extends WeddingController {

	public static Form<Hiring> hiringForm = Form.form(Hiring.class);

	public static Result hiring() {
		Hiring hiring = HiringDAO.getHiringDAO().find().get();
		return ok(views.html.hirings.hiring.render(getHirings(),hiring));
	}
	
	public static List<Hiring> getHirings(){
		return HiringDAO.getHiringDAO().getAllHirings();
	}
	
	//FIXME cualquiera ¡¡¡
	public static Result getConversation(String name){
		Hiring hiring = HiringDAO.getHiringDAO().findOne("name",name);
		return ok(views.html.hirings.conversation.render(hiring));
	}
	

}
