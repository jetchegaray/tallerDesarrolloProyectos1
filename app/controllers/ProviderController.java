package controllers;


import java.util.List;
import java.util.Map;

import model.dao.HiringDAO;
import model.dao.ProviderDAO;

import model.domain.Hiring;
import model.domain.Provider;

import play.libs.Json;
import play.mvc.Result;
import org.bson.types.ObjectId;

import views.html.providers.*;
import play.data.DynamicForm;

import com.google.common.collect.Maps;

public class ProviderController extends WeddingController {

	public static Result getProviderByType(String type){
		List<Provider> providers = ProviderDAO.getProviderDAO().getProviderByType(type);
		Map<String, List<Provider>> mapa = Maps.<String, List<Provider>>newHashMap();
		mapa.put("providers", providers);
		return ok(Json.toJson(mapa));
	}


	public static Result show(String id, String eventType, String taskSlug) {
		Provider provider = ProviderDAO.getProviderDAO().get(new ObjectId(id));
		return ok( show.render(provider, eventType, taskSlug) );
	}

	public static Result hire(String id) {
		DynamicForm form = new DynamicForm().bindFromRequest();

		Hiring hiring = new Hiring();
		hiring.wedding = currentWedding();
		hiring.provider = ProviderDAO.getProviderDAO().get(new ObjectId(id));
		hiring.eventType = form.get("eventType");
		hiring.taskSlug = form.get("taskSlug");
		hiring.addConversationMessageUser(form.get("message"));

		HiringDAO.getHiringDAO().save(hiring);

		return redirect( hiring.getTask().getUrl() );
	}
}
