package model.dao;

import java.util.List;

import model.domain.Provider;

import org.bson.types.ObjectId;

import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;

public class ProviderDAO extends BasicDAO<Provider,ObjectId>{

	private static ProviderDAO instance = new ProviderDAO();

	private ProviderDAO() {
		super(DaoConfiguration.mongo, DaoConfiguration.morphia, DaoConfiguration.dBAddress.getDatabase());
	}

	public static ProviderDAO getProviderDAO(){
		return ProviderDAO.instance;
	}

	public List<Provider> getProviderByType(String type) {
		Query<Provider> query = super.ds.createQuery(Provider.class).field("type").equal(type);
		return getProviderDAO().find(query).asList();
	}
}
