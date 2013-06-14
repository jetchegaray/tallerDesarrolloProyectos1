package controllers;

import model.domain.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class UserController extends Controller {
  
	public static Form<User> userForm = Form.form(User.class);
    
    public static Result user() {
        return ok(user.render(userForm));
    }
    
    public static Result addUser(){
    	
    	Form<User> filledForm = userForm.bindFromRequest();

		if(filledForm.hasErrors()) {
			return badRequest("TODO MAL");
		} else {
			User.create(filledForm.get());
			return redirect(routes.Mockup.at("Dashboard"));
		}
    }

}