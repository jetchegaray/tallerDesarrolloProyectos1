package controllers;

import model.dao.WeddingDAO;
import model.domain.Wedding;

import org.bson.types.ObjectId;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;

@Security.Authenticated(WeddingAuthenticator.class)
@With(WeddingContext.class)
public class WeddingController extends Controller {

	public static Wedding currentWedding() {
		return WeddingDAO.instance.get(new ObjectId(WeddingAuthenticator.getWeddingId(session())));
	}

}
