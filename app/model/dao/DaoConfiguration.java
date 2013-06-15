package model.dao;

import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import com.mongodb.MongoURI;

public class DaoConfiguration {

	public static MongoURI mongoURI;
	
	public static Mongo mongo;
	
	public static Morphia morphia = new Morphia();
}
