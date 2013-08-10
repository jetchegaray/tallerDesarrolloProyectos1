package model.domain.events;

import model.domain.Event;
import model.domain.Task;

public class Party extends Event {

	public String city;

	public Party() {
		super("Fiesta");
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
