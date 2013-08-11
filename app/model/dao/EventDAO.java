package model.dao;

import model.domain.Event;

import org.bson.types.ObjectId;

import com.google.code.morphia.dao.BasicDAO;

public class EventDAO extends BasicDAO<Event, ObjectId> {

	public static final EventDAO instance = new EventDAO();

	private EventDAO() {
		super(DaoConfiguration.mongo, DaoConfiguration.morphia, DaoConfiguration.dBAddress.getDatabase());
	}

	public void merge(Event event) {
		ds.merge(event);
	}
}
