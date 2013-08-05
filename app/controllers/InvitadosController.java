package controllers;

import java.util.List;
import java.util.ArrayList;

import model.dao.GuestDAO;
import model.domain.User;
import model.domain.guests.Guest;
import model.domain.guests.InvitationStatus;
import org.bson.types.ObjectId;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Result;
import views.html.invitados.*;
import views.html.user.signup;

public class InvitadosController extends WeddingController {

	static List<Guest> guestList = new ArrayList<Guest>();


	public static Form<Guest> guestForm = Form.form(Guest.class);



	public static Result index() {
		List<Guest> guestQueryList = GuestDAO.getGuestDAO().listGuests(currentWedding());

		return ok(invitados.render(guestQueryList, guestForm));
	}

    public static Result addGuest(){

        System.out.println("Vamos a agregar un usuario");
        //Form<Guest> filledForm = guestForm.bindFromRequest();
        DynamicForm params = new DynamicForm().bindFromRequest();
        String guestName = params.get("name");
        String guestEmail = params.get("email");
        ObjectId weddingId = new ObjectId(currentWedding().getId());
        System.out.println("Fillled form: " + params.toString());

        Guest nuevoInvitado = new Guest(guestName,guestEmail,weddingId) ;
        System.out.println("Id" + weddingId.toString());
        Guest.createInDB(nuevoInvitado);
        return redirect(routes.InvitadosController.index());

    }
}
