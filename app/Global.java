import play.Application;
import play.GlobalSettings;

import com.google.code.morphia.logging.MorphiaLoggerFactory;
import com.google.code.morphia.logging.slf4j.SLF4JLogrImplFactory;

public class Global extends GlobalSettings {

	
	@Override
	
	public void beforeStart(Application app) {
		MorphiaLoggerFactory.registerLogger(SLF4JLogrImplFactory.class);
	}
	
	
	@Override
	
	public void onStart(Application app) {
	
	}


}
