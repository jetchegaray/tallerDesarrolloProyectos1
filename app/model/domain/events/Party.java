package model.domain.events;

import model.domain.Event;
import model.domain.Task;
import model.domain.tasks.*;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Party extends Event {

	public static final String[] EXTRAS = { "Banda", "DJ", "Cotillón", "Decoración especial",
            "Fotógrafo", "Filmación", "Fuegos artificiales" };

	public String when;
	public List<String> extras;

	public Party() {
		super("Fiesta");
		extras = new ArrayList<String>();
	}

	public Party(Date date) {
		this();
		this.date = date;
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
				if (task == null) addExtra(taskName, extra);
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

	private void addExtra(String taskName, String extra) {
		Task task = null;

		if (extra.equals("Banda") || extra.equals("DJ")) {
			task = new HireProvider(this, taskName, 1000, daysBefore(30 * 2), "Musica");
		}
		if (extra.equals("Fotógrafo")) {
			task = new HireProvider(this, taskName, 1500, daysBefore(30 * 2), "Fotos");
		}
		if (extra.equals("Filmación")) {
			task = new HireProvider(this, taskName, 2000, daysBefore(30 * 2), "Videos");
		}
		if (task == null) {
			task = new SimpleHire(this, taskName, 2000, daysBefore(30));
		}

		addTask(task);

	}
}
