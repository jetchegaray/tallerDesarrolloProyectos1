import play.Application;
import play.GlobalSettings;

import com.google.code.morphia.logging.MorphiaLoggerFactory;
import com.google.code.morphia.logging.slf4j.SLF4JLogrImplFactory;
import java.net.UnknownHostException;
import com.mongodb.Mongo;
import com.mongodb.MongoURI;
import model.dao.DaoConfiguration;

public class Global extends GlobalSettings {

	
	@Override
	
	public void beforeStart(Application app) {
		MorphiaLoggerFactory.registerLogger(SLF4JLogrImplFactory.class);
		try {
			//mongodb://127.0.0.1:27017/bodas_db
			DaoConfiguration.mongoURI = new MongoURI(app.configuration().getString("mongodb.uri"));
			DaoConfiguration.mongo = new Mongo(DaoConfiguration.mongoURI);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	
	public void onStart(Application app) {
	
	}


}
