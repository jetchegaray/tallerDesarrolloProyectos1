package model.domain.guests;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import model.dao.GuestDAO;
import org.bson.types.ObjectId;
import play.data.validation.Constraints;
import play.data.validation.Constraints.Required;

@Entity("guests")
public class Guest {


    @Id
    private ObjectId id;

    @Required
    private ObjectId weddingId;

    private String email;
    private String roomLocation;
    @Required
    private String name;
    @Required
    private InvitationStatus statusCivil;
    @Required
    private InvitationStatus statusFiesta;
    @Required
    private InvitationStatus statusIglesia;

    public Guest() {
    }

    public Guest(String name, String email, ObjectId weddingId) {
        this.email = email;
        this.name = name;
        this.statusCivil= InvitationStatus.NOINVITADO;
        this.statusFiesta= InvitationStatus.NOINVITADO;
        this.statusIglesia= InvitationStatus.NOINVITADO;
        this.roomLocation = null;
        this.weddingId= weddingId;
    }

    public Guest(String email, String roomLocation, String name,ObjectId weddingId) {
        this.email = email;
        this.roomLocation = roomLocation;
        this.name = name;
        this.statusCivil= InvitationStatus.NOINVITADO;
        this.statusFiesta= InvitationStatus.NOINVITADO;
        this.statusIglesia= InvitationStatus.NOINVITADO;
        this.weddingId= weddingId;
    }



    public String getEmail() {
        return email;
    }

    public String getRoomLocation() {
        return roomLocation;
    }

    public String getName() {
        return name;
    }

    public InvitationStatus getStatusCivil() {
        return statusCivil;
    }

    public InvitationStatus getStatusFiesta() {
        return statusFiesta;
    }

    public InvitationStatus getStatusIglesia() {
        return statusIglesia;
    }

    public void setStatusIglesia(InvitationStatus statusIglesia) {
        this.statusIglesia = statusIglesia;
    }

    public void setStatusCivil(InvitationStatus statusCivil) {
        this.statusCivil = statusCivil;
    }

    public void setStatusFiesta(InvitationStatus statusFiesta) {
        this.statusFiesta = statusFiesta;
    }

    public static void createInDB(Guest guest){
        GuestDAO.getGuestDAO().save(guest);
        System.out.println("Guardando un nuevo usuario");
    }


}

