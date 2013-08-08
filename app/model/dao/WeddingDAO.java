package model.dao;

import model.domain.Wedding;

import org.bson.types.ObjectId;

import com.google.code.morphia.dao.BasicDAO;

public class WeddingDAO extends BasicDAO<Wedding, ObjectId> {

	public static final WeddingDAO instance = new WeddingDAO();

	private WeddingDAO() {
		super(DaoConfiguration.mongo, DaoConfiguration.morphia, DaoConfiguration.dBAddress.getDatabase());
	}

	public void merge(Wedding wedding) {
		ds.merge(wedding);
	}
}
