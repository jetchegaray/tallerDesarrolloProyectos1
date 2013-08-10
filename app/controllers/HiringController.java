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
		Hiring hiring = HiringDAO.getHiringDAO().find().get();
		return ok(views.html.hirings.hiring.render(getHirings(),hiring));
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
		return ok(views.html.hirings.conversation.render(hiring));
	}
	

}
