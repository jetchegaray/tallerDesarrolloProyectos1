package controllers;

import java.util.ArrayList;
import java.util.List;

import model.dao.GuestDAO;
import model.domain.guests.Guest;
import play.data.Form;
import play.mvc.Result;
import views.html.invitados.form;
import views.html.invitados.invitados;

public class InvitadosController extends WeddingController {

	static List<Guest> guestList = new ArrayList<Guest>();


	public static Form<Guest> guestForm = Form.form(Guest.class);



	public static Result index() {
		List<Guest> guestQueryList = GuestDAO.getGuestDAO().listGuests(currentWedding());

		return ok(invitados.render(guestQueryList, guestForm));
	}

	public static Result addGuest(){
		Form<Guest> filledForm = guestForm.bindFromRequest();

		if (filledForm.hasErrors()) {
			return badRequest(form.render(filledForm));
		}

		Guest guest   = filledForm.get();
		guest.setWedding(currentWedding());

		GuestDAO.getGuestDAO().save(guest);
		return redirect(routes.InvitadosController.index());

	}
}
