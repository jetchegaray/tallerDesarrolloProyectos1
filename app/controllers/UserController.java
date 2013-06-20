package controllers;

import model.domain.User;
import model.dao.UserDAO;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.user.*;

public class UserController extends Controller {

	public static Form<User> userForm = Form.form(User.class);

    public static Result user() {
        return ok(signup.render(userForm));
    }

    public static Result getLogin() {
        return ok(login.render(userForm));
    }

    public static Result login() {
        DynamicForm params = new DynamicForm().bindFromRequest();
        String userName = params.get("userName");
        String password = params.get("password");
        User dbUser = UserDAO.getUserDAO().findOne("userName", userName);

        if ((dbUser != null) && dbUser.password.equals(password)) {
            session("userId", dbUser.userName);
            return redirect(routes.Mockup.at("Dashboard"));
        } else {
            Form<User> filledForm = userForm.bindFromRequest();
            return badRequest(login.render(filledForm));
        }
    }

    public static Result addUser(){

    	Form<User> filledForm = userForm.bindFromRequest();

		if(filledForm.hasErrors()) {
			return badRequest(signup.render(filledForm));
		} else {
			User.create(filledForm.get());
			return redirect(routes.Mockup.at("Dashboard"));
		}
    }

}
