package model.domain.events;

import model.domain.Event;
import model.domain.Task;
import model.domain.tasks.SimpleHire;

import java.util.List;
import java.util.ArrayList;

public class Party extends Event {

	public static final String[] EXTRAS = { "Banda", "DJ", "Cotillón", "Decoración especial",
            "Fotógrafo", "Filmación", "Fuegos artificiales" };

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
		ensureExtrasTasks();
		for(Task task : getPendingTasks()) {
			task.updatePricingEstimate(this);
		}
	}

	private void ensureExtrasTasks() {
		for(String extra : EXTRAS) {
			String taskName = "Contratar " + extra;
			Task task = findTaskBySlug(slugify(taskName));

			if (wantsExtra(extra)) {
				if (task == null) addTask(new SimpleHire(this, taskName, 2000));
			} else {
				if (task != null) tasks.remove(task);
			}
		}
	}

	private enum Style {
		DAY, NIGHT
	}

	private boolean wantsExtra(String extra) {
		return extras.contains(extra);
	}

	// To be removed
	private String slugify(String string) {
		return string.replaceAll("\\W","-").toLowerCase();
	}

}
