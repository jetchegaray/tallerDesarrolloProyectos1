package controllers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import play.api.templates.Html;
import play.mvc.Result;
import scala.collection.mutable.StringBuilder;
import views.html.account;

public class Mockup extends Application {

	public static Result at(String mockup) {
		try {
			StringBuilder content = new StringBuilder();
			List<String> fileContent = Files.readAllLines(Paths.get("app/views/mockups/" + mockup + ".html"), StandardCharsets.UTF_8);
			for(String line : fileContent) {
				content.append(line);
			}
			return ok(account.render(mockup, new Html(content)));
		} catch(IOException e) {
			return notFound();
		}
	}

}
