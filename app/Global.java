import play.Application;
import play.GlobalSettings;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.logging.MorphiaLoggerFactory;
import com.google.code.morphia.logging.slf4j.SLF4JLogrImplFactory;
import com.google.code.morphia.mapping.DefaultCreator;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Embedded;
import com.mongodb.DBObject;

import java.net.UnknownHostException;
import java.lang.RuntimeException;
import java.util.Set;
import java.util.HashSet;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import model.dao.DaoConfiguration;
import play.libs.Classpath;

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
		mapClasses(app);
	}

	private void mapClasses(final Application app) {
		// Register all models.Class
		try {
			Datastore ds = DaoConfiguration.morphia.createDatastore(DaoConfiguration.mongo, DaoConfiguration.dBAddress.getDatabase());
			Set<String> classes = new HashSet<String>();
			classes.addAll(Classpath.getTypesAnnotatedWith(app, "model.domain", Entity.class));
			classes.addAll(Classpath.getTypesAnnotatedWith(app, "model.domain", Embedded.class));

			for (String clazz : classes) {
				DaoConfiguration.morphia.map(Class.forName(clazz, true, app.classloader()));
			}
			// @see http://code.google.com/p/morphia/wiki/Datastore#Ensure_Indexes_and_Caps
			ds.ensureCaps(); //creates capped collections from @Entity
			ds.ensureIndexes(); //creates indexes from @Index annotations in your entities

		} catch(ClassNotFoundException e) {

		}
  }
}
