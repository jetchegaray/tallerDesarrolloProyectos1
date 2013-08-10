package model.domain.events;

import model.domain.Event;
import model.domain.Task;

import java.util.List;
import java.util.ArrayList;

public class Party extends Event {

	public String city;
	public String when;
	public List<String> extras;

	public Party() {
		super("Fiesta");
		extras = new ArrayList<String>();
	}

	public String getTypeName() {
		return EventType.PARTY.toString();
	}

	public void updateTasks() {
		for(Task task : getPendingTasks()) {
			task.updatePricingEstimate(this);
		}
	}

	private void ensurePhotographerTask() {
		if (findTaskBySlug("contratar-fotografo") == null) {

		}
	}

	private enum Style {
		DAY, NIGHT
	}
}
