package model.dao;

import com.github.jmkgreen.morphia.Morphia;
import com.mongodb.Mongo;
import com.mongodb.MongoURI;

import com.github.jmkgreen.morphia.logging.MorphiaLoggerFactory;
import com.github.jmkgreen.morphia.logging.slf4j.SLF4JLogrImplFactory;

public class DaoConfiguration {

	public static MongoURI mongoURI;

	public static Mongo mongo;

	public static Morphia morphia = new Morphia();
}
