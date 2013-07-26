package controllers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

import play.api.templates.Html;
import play.mvc.Result;
import scala.collection.mutable.StringBuilder;
import views.html.invitados.*;

public class InvitadosController extends Application {

	static List<String> listaInvitados = new ArrayList<String>();

	static {
		listaInvitados.add("Rodolfo Cruz");
		listaInvitados.add("Martin Ciruzzi");
	}

	public static Result at(String title) {
		
			return ok(invitados.render(title, listaInvitados));						
		
			
		
	}
}
