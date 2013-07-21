package model.dao;

import model.domain.User;

import org.bson.types.ObjectId;

import com.google.code.morphia.dao.BasicDAO;

public class UserDAO extends BasicDAO<User,ObjectId>{

	private static UserDAO instance = new UserDAO();

	private UserDAO() {
		super(DaoConfiguration.mongo, DaoConfiguration.morphia, DaoConfiguration.dBAddress.getDatabase());
	}

	public static UserDAO getUserDAO(){
		return UserDAO.instance;
	}

}
