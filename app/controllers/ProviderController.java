package controllers;


import java.util.List;
import java.util.Map;

import model.dao.ProviderDAO;
import model.domain.Provider;
import play.libs.Json;
import play.mvc.Result;
import org.bson.types.ObjectId;

import views.html.providers.*;

import com.google.common.collect.Maps;

public class ProviderController extends WeddingController {

	public static Result getProviderByType(String type){
		List<Provider> providers = ProviderDAO.getProviderDAO().getProviderByType(type);
		Map<String, List<Provider>> mapa = Maps.<String, List<Provider>>newHashMap();
		mapa.put("providers", providers);
		return ok(Json.toJson(mapa));
	}


	public static Result show(String id) {
		Provider provider = ProviderDAO.getProviderDAO().get(new ObjectId(id));
		return ok( show.render(provider) );
	}
}
