package model.domain;

import model.domain.events.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;

import org.bson.types.ObjectId;
import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Converters;
import extensions.morphia.BigDecimalConverter;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.PredicateUtils;

@Converters({BigDecimalConverter.class})
@Entity("events")
public abstract class Event implements Budgetable {

	public @Id ObjectId id;
	public String name;
	public Date date;
	public Integer guestEstimate;
	public BigDecimal budget;
	public @Embedded List<Task> tasks;
	public @Embedded List<Expense> expenses;

	public String city;

	public Event() {
		this("Unnamed... :(");
	}

	public Event(String name) {
		this.name = name;
		this.tasks = new ArrayList<Task>();
		this.expenses = new ArrayList<Expense>();
		this.budget = new BigDecimal(0);
	}

	public List<Task> getAllTasks() {
		return tasks;
	}

	@SuppressWarnings("unchecked")
	public List<Task> getPendingTasks() {
		return (List<Task>)CollectionUtils.select(tasks, PredicateUtils.invokerPredicate("isPending"));
	}

	public List<Expense> getActiveExpenses() {
		// We should filter expenses that have been canceled or rejected
		return expenses;
	}

	public static Class<? extends Event> getType(String str) {
		EventType type = EventType.valueOf(str);

		switch (type) {
			case CIVIL:    return Civil.class;
			case CEREMONY: return Ceremony.class;
			case PARTY:    return Party.class;
			default: throw new RuntimeException("Unknown event type: " + str);
		}
	}

	// Allow to reverse getType
	public abstract String getTypeName();

	// Amount to spend by the budgeteable
	public BigDecimal getBudget() {
		return budget;
	}

	@SuppressWarnings("unchecked")
	public Collection<Costable> getPendingCostableTasks() {
		return (Collection<Costable>)CollectionUtils.select(tasks, PredicateUtils.instanceofPredicate(Costable.class));
	}

	// Lower end for the current estimated cost
	public BigDecimal getLowerEstimate() {
		BigDecimal acum = new BigDecimal(0);
		for(Costable task : getPendingCostableTasks()) {
			acum = acum.add(task.getLowerEstimate());
		}
		return acum.add(getAmountSpent());
	}

	// Upper end for the current estimated cost
	public BigDecimal getUpperEstimate() {
		BigDecimal acum = new BigDecimal(0);
		for(Costable task : getPendingCostableTasks()) {
			acum = acum.add(task.getUpperEstimate());
		}
		return acum.add(getAmountSpent());
	}

	// Costs already spent
	public BigDecimal getAmountSpent() {
		BigDecimal acum = new BigDecimal(0);
		for(Expense expense : getActiveExpenses()) {
			acum = acum.add(expense.getAmountSpent());
		}
		return acum;
	}

	// Budget minus spent
	public BigDecimal getAmountAvailable() {
		return getBudget().subtract(getAmountSpent());
	}

	// Amount that has to be paid in the future
	public BigDecimal getAmountComprised() {
		BigDecimal acum = new BigDecimal(0);
		for(Expense expense : getActiveExpenses()) {
			acum = acum.add(expense.getAmountComprised());
		}
		return acum;
	}

	public BigDecimal getTotalCost() {
		BigDecimal acum = new BigDecimal(0);
		for(Expense expense : getActiveExpenses()) {
			acum = acum.add(expense.getTotal());
		}
		return acum;
	}

	public void addTask(Task t) {
		// FIXME: Using name for now, because its used on urls...
		// t.eventType = getTypeName();
		t.eventType = name.toLowerCase();
		tasks.add(t);
	}

	public void addExpense(Expense expense) {
		expense.eventOrigin = getTypeName();
		expenses.add(expense);
	}

	public Task findTaskBySlug(final String slug) {
		return (Task)CollectionUtils.find(tasks, new Predicate() {
			public boolean evaluate(Object object) {
				return slug.equals(((Task)object).slug);
			}
		});
	}

	public abstract void updateTasks();

	protected enum EventType {
		CIVIL, CEREMONY, PARTY
	}

}
