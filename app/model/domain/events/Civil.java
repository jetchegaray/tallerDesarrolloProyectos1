package model.domain.events;

import model.domain.Event;
import model.domain.Task;
import model.domain.tasks.*;
import com.google.code.morphia.annotations.Embedded;
import java.math.BigDecimal;

public class Civil extends Event {

	public Boolean withReception;

	public Civil() {
		super("Civil");
	}

	public String getTypeName() {
		return EventType.CIVIL.toString();
	}
	
}
