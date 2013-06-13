package model.dao;

import java.net.UnknownHostException;

import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

public class DaoConfiguration {

	public static String DB_NAME = "boda_db";
	
	public static Mongo mongo;
	
	static{
		try {
			mongo = new Mongo();//127.0.0.1:27017
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} 
	}
	
	public static Morphia morphia = new Morphia();
}
