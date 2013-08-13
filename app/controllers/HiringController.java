package controllers;


import java.util.List;

import model.dao.HiringDAO;
import model.domain.Hiring;

import org.bson.types.ObjectId;

import play.data.Form;
import play.mvc.Result;

public class HiringController extends WeddingController {

	public static Form<Hiring> hiringForm = Form.form(Hiring.class);

	public static Result hiring() {
		return ok(views.html.hirings.hiring.render(getHirings()));
	}

	public static List<Hiring> getHirings(){
		return HiringDAO.getHiringDAO().listHirings(currentWedding());
	}

	public static Result show(String id){
		Hiring hiring = HiringDAO.getHiringDAO().get(new ObjectId(id));

		return ok( views.html.hirings.conversation.render(hiring) );
	}


}
