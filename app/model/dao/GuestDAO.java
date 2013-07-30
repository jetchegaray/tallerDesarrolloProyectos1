package model.dao;

import com.google.code.morphia.DatastoreImpl;
import com.google.code.morphia.dao.BasicDAO;
import model.domain.guests.Guest;
import org.bson.types.ObjectId;

/**
 * Created with IntelliJ IDEA.
 * User: martin
 * Date: 7/29/13
 * Time: 9:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class GuestDAO extends BasicDAO<Guest,ObjectId> {

private static GuestDAO instance = new GuestDAO();

private GuestDAO() {
        super(DaoConfiguration.mongo, DaoConfiguration.morphia, DaoConfiguration.dBAddress.getDatabase());
}

public static GuestDAO getGuestDAO(){
        return GuestDAO.instance;
}
/*public DatastoreImpl getDataStoreForQuery(){
    return  super.getDataStore();
} */



}
