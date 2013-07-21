package controllers;

import play.mvc.Security;
import play.mvc.Result;
import play.mvc.Http.*;

import model.services.WeddingCreationService;
import model.domain.Wedding;

public class WeddingAuthenticator extends Security.Authenticator {

	@Override
	public String getUsername(Context ctx) {
		String weddingId = WeddingAuthenticator.getWeddingId(ctx.session());

		if (weddingId != null) return weddingId;

		// XXX: Auto generate weddings for easier testing
		Wedding wedding = new WeddingCreationService().createWedding(new Wedding());
		WeddingAuthenticator.setWeddingId(ctx.session(), wedding.getId());

		return wedding.getId();
	}

	@Override
	public Result onUnauthorized(Context ctx) {
		return redirect(routes.UserController.getLogin());
	}

	public static String getWeddingId(Session session) {
		return session.get("weddingId");
	}

	public static void setWeddingId(Session session, String id) {
		session.put("weddingId", id);
	}

}
