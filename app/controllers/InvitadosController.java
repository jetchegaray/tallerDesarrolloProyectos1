package controllers;

import java.util.List;
import java.util.ArrayList;

import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.QueryResults;
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

    public static Form<Guest> guestForm = Form.form(Guest.class);



	public static Result index() {
            session("userId", "Martin");  //TODO take it from session para filtrar en la query

            String userId = session("userId");//"Martin"; //
            Query<Guest> q = GuestDAO.getGuestDAO().getDatastore().createQuery(Guest.class);
            //q.field("name").equal("Martin");

            QueryResults<Guest> guestsFound = GuestDAO.getGuestDAO().find(q);
            List<Guest> guestQueryList =guestsFound.asList();
            //createDummyGuests();

			return ok(invitados.render(guestQueryList, guestForm));
	}

    public static void createDummyGuests(){
//        Guest rodo = new Guest("Rodolfo Cruz","a@b",new ObjectId());
//        Guest tincho = (new Guest("Martin Ciruzzi","c@d",new ObjectId()));
//        tincho.setStatusCivil(InvitationStatus.ASISTE);
//        tincho.setStatusIglesia(InvitationStatus.ASISTE);
//        tincho.setStatusFiesta(InvitationStatus.ASISTE);
//
//        rodo.setStatusCivil(InvitationStatus.INVITADO);
//        rodo.setStatusIglesia(InvitationStatus.INVITADO);
//        rodo.setStatusFiesta(InvitationStatus.INVITADO);

        //guestList.add(tincho);
        //guestList.add(rodo);

        System.out.println("HOLAAAAAAAAAAAAAAAAAA RODODOOOOOOOOOOOOOOOO");
        Guest.createInDB(guestList.get(0));
        Guest.createInDB(guestList.get(1));

    }


    public static Result addGuest(){


        System.out.println("Vamos a agregar un usuario");
        //Form<Guest> filledForm = guestForm.bindFromRequest();
        DynamicForm params = new DynamicForm().bindFromRequest();
        String guestName = params.get("name");
        String guestEmail = params.get("email");
        ObjectId weddingId = new ObjectId();              //TODO take it from session para guardarlo
        System.out.println("Fillled form: " + params.toString());

        Guest nuevoInvitado = new Guest(guestName,guestEmail,weddingId) ;
        Guest.createInDB(nuevoInvitado);
        return redirect(routes.InvitadosController.index());

    }
}
