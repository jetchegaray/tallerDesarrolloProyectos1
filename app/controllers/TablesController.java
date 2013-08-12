package controllers;

import java.util.List;
import java.util.ArrayList;

import model.dao.GuestDAO;
import model.domain.User;
import model.domain.guests.Guest;

import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Result;

import views.html.tasks.tables;

public class TablesController extends WeddingController {

	public static Result index() {
        List<Guest> guestQueryList = GuestDAO.getGuestDAO().listGuests(currentWedding());

		return ok(tables.render(guestQueryList));
	}
}
