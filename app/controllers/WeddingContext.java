package controllers;

import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;

import model.services.WeddingCreationService;
import model.domain.Wedding;
import org.bson.types.ObjectId;
import model.dao.WeddingDAO;

public class WeddingContext extends Action.Simple {

	@Override
	public Result call(Context ctx) throws Throwable {
		String weddingId = WeddingAuthenticator.getWeddingId(ctx.session());
		Wedding wedding  = WeddingDAO.instance.get(new ObjectId(weddingId));

		if (wedding != null) {
			ctx.args.put("wedding", wedding);
			return delegate.call(ctx);
		} else {
			WeddingAuthenticator.signout(ctx.session());
			return redirect(routes.UserController.getLogin());
		}
	}

	public static Wedding current() {
		Wedding wedding = (Wedding)Context.current().args.get("wedding");

		if (wedding == null) throw new RuntimeException("Use this ONLY inside a WeddingContext");

		return wedding;
	}
}
