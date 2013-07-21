package controllers;

import model.dao.WeddingDAO;
import model.domain.Wedding;

import org.bson.types.ObjectId;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

@Security.Authenticated(WeddingAuthenticator.class)
public class WeddingController extends Controller {

	public static Wedding currentWedding() {
		return WeddingDAO.instance.get(new ObjectId(WeddingAuthenticator.getWeddingId(session())));
	}

}
