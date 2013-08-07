package model.dao;

import java.util.List;

import model.domain.Hiring;
import model.domain.Hiring.HiringType;

import org.bson.types.ObjectId;

import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;
import com.google.common.collect.Lists;

public class HiringDAO extends BasicDAO<Hiring,ObjectId>{

	private static HiringDAO instance = new HiringDAO();

	private HiringDAO() {
		super(DaoConfiguration.mongo, DaoConfiguration.morphia, DaoConfiguration.dBAddress.getDatabase());
	}

	public static HiringDAO getHiringDAO(){
		return HiringDAO.instance;
	}

	public List<Hiring> getAllHirings() {
		//return getHiringDAO().find().asList();
		return Lists.newArrayList();
	}
	
	
	

	public List<Hiring> getHiringByType(String type) {
		Query<Hiring> query = super.ds.createQuery(Hiring.class).field("type").equal(type);
		return getHiringDAO().find(query).asList();
	}

}
