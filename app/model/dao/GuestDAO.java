package model.dao;

import com.google.code.morphia.DatastoreImpl;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.QueryResults;

import model.domain.guests.Guest;
import model.domain.Wedding;
import org.bson.types.ObjectId;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: martin
 * Date: 7/29/13
 * Time: 9:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class GuestDAO extends BasicDAO<Guest, ObjectId> {

	private static GuestDAO instance = new GuestDAO();

	private GuestDAO() {
		super(DaoConfiguration.mongo, DaoConfiguration.morphia, DaoConfiguration.dBAddress.getDatabase());
	}

	public static GuestDAO getGuestDAO(){
		return GuestDAO.instance;
	}

	public List<Guest> listGuests(Wedding wedding) {
		return guestsForWedding(wedding).asList();
	}

	private QueryResults<Guest> guestsForWedding(Wedding wedding) {
		Query<Guest> query = createQuery();
		query.field("wedding").equal(wedding);

		return find(query);
	}
}
