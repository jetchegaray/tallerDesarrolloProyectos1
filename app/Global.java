import play.Application;
import play.GlobalSettings;

import com.google.code.morphia.logging.MorphiaLoggerFactory;
import com.google.code.morphia.logging.slf4j.SLF4JLogrImplFactory;
import com.google.code.morphia.mapping.DefaultCreator;
import com.mongodb.DBObject;

import java.net.UnknownHostException;
import java.lang.RuntimeException;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import model.dao.DaoConfiguration;

public class Global extends GlobalSettings {


	@Override
	public void beforeStart(final Application app) {

		MorphiaLoggerFactory.reset();
		MorphiaLoggerFactory.registerLogger(SLF4JLogrImplFactory.class);

		try {
			DaoConfiguration.dBAddress = new MongoClientURI(app.configuration().getString("mongodb.uri"));
			DaoConfiguration.mongo = new MongoClient(DaoConfiguration.dBAddress);
		} catch (UnknownHostException e) {
			throw new RuntimeException("Couldn't connect to mongo", e);
		}

		// Reconfigure Morphia class loader. Fixes java.lang.InstantiationException
		// when using polymorphic embedded associations
		DaoConfiguration.morphia.getMapper().getOptions().objectFactory = new DefaultCreator() {
			@Override protected ClassLoader getClassLoaderForClass(String clazz, DBObject object) {
				return app.classloader();
			}
		};
	}

}
