package controllers;

import controllers.Application;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

import play.*;
import play.mvc.*;
import play.api.templates.*;

import scala.collection.mutable.StringBuilder;
import java.util.List;
import java.io.IOException;

import views.html.*;

public class Mockup extends Application {

	public static Result at(String mockup) {
		try {
			StringBuilder content = new StringBuilder();
			List<String> fileContent = Files.readAllLines(Paths.get("app/views/mockups/" + mockup + ".html"), StandardCharsets.UTF_8);
			for(String line : fileContent) {
				content.append(line);
			}
			return ok(main.render(mockup, new Html(content)));
		} catch(IOException e) {
			return notFound();
		}
	}

}