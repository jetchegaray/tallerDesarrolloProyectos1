package model.domain;

import model.dao.UserDAO;

import org.bson.types.ObjectId;

import play.data.validation.Constraints.Required;

import com.github.jmkgreen.morphia.annotations.Entity;
import com.github.jmkgreen.morphia.annotations.Id;

@Entity("users")
public class User {

	@Id
	ObjectId id;

	@Required
	public String userName;

	@Required
	public String password;

	@Required
	public String email;


	public static void create(User user){
		UserDAO.getUserDAO().save(user);
	}

}
