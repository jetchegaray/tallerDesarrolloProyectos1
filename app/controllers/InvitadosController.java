package controllers;

import java.util.List;
import java.util.ArrayList;

import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.QueryResults;
import model.dao.GuestDAO;
import model.domain.guests.Guest;
import model.domain.guests.InvitationStatus;
import org.bson.types.ObjectId;
import play.mvc.Result;
import views.html.invitados.*;

public class InvitadosController extends Application {

	static List<Guest> guestList = new ArrayList<Guest>();


    //prueba con valores hardcodeados
	static {
        /*Guest rodo = new Guest("Rodolfo Cruz","a@b",new ObjectId());
        Guest tincho = (new Guest("Martin Ciruzzi","c@d",new ObjectId()));
        tincho.setStatusCivil(InvitationStatus.ASISTE);
        tincho.setStatusIglesia(InvitationStatus.ASISTE);
        tincho.setStatusFiesta(InvitationStatus.ASISTE);

        rodo.setStatusCivil(InvitationStatus.INVITADO);
        rodo.setStatusIglesia(InvitationStatus.INVITADO);
        rodo.setStatusFiesta(InvitationStatus.INVITADO);


		guestList.add(tincho);
		guestList.add(rodo);*/
    }



	public static Result at(String title) {
            session("userId", "Martin"); //hardcoded session to test - comment later

            String userId = session("userId");//"Martin"; //
            Query<Guest> q = GuestDAO.getGuestDAO().getDatastore().createQuery(Guest.class);
            //q.field("name").equal("Martin");

            QueryResults<Guest> guestsFound = GuestDAO.getGuestDAO().find(q);
            List<Guest> guestQueryList =guestsFound.asList();
            createGuests();

			return ok(invitados.render(title, guestQueryList));
	}

    public static void createGuests(){
        Guest rodo = new Guest("Rodolfo Cruz","a@b",new ObjectId());
        Guest tincho = (new Guest("Martin Ciruzzi","c@d",new ObjectId()));
        tincho.setStatusCivil(InvitationStatus.ASISTE);
        tincho.setStatusIglesia(InvitationStatus.ASISTE);
        tincho.setStatusFiesta(InvitationStatus.ASISTE);

        rodo.setStatusCivil(InvitationStatus.INVITADO);
        rodo.setStatusIglesia(InvitationStatus.INVITADO);
        rodo.setStatusFiesta(InvitationStatus.INVITADO);


        guestList.add(tincho);
        guestList.add(rodo);

        System.out.println("HOLAAAAAAAAAAAAAAAAAA RODODOOOOOOOOOOOOOOOO");
        Guest.create(guestList.get(0));
        Guest.create(guestList.get(1));

    }


    public static Result addGuest(){                      //TODO


        return redirect(routes.InvitadosController.at());

    }
}
