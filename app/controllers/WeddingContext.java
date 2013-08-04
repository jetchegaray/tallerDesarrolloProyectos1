package controllers;

import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;

import model.domain.Wedding;

public class WeddingContext extends Action.Simple {

	@Override
	public Result call(Context ctx) throws Throwable {
		ctx.args.put("wedding", WeddingController.currentWedding());
		return delegate.call(ctx);
	}

	public static Wedding current() {
		Wedding wedding = (Wedding)Context.current().args.get("wedding");

		if (wedding == null) throw new RuntimeException("Use this ONLY inside a WeddingContext");

		return wedding;
	}
}
