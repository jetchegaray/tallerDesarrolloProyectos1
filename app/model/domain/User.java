package model.domain;

import model.dao.UserDAO;

import org.bson.types.ObjectId;

import play.data.validation.Constraints.Required;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;

@Entity("users")
public class User {

	@Id ObjectId id;

	@Required public String password;
	@Required public String email;

	@Reference(ignoreMissing = true)
	public Wedding wedding;

	public static User create(User user){
		UserDAO.getUserDAO().save(user);
		return user;
	}

	public String getId() {
		return id.toString();
	}

}
