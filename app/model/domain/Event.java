package model.domain;

import model.domain.events.*;

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

@Converters({BigDecimalConverter.class})
public abstract class Event implements Budgetable {

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
	}

	public void addTask(Task t) {
		tasks.add(t);
	}

	public List<Task> getAllTasks() {
		return tasks;
	}
	
	public List<Task> getPendingTasks() {
		// We should filter tasks that have been completed or canceled (not done)
		List<Task> tasksAux = new ArrayList<Task>();
		for(Task task : getAllTasks()) {
			if (! task.done){
				tasksAux.add(task);
			}
		}
		return tasksAux;
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

	// Lower end for the current estimated cost
	public BigDecimal getLowerEstimate() {
		BigDecimal acum = new BigDecimal(0);
		for(Task task : getAllTasks()) {
			acum = acum.add(task.getLowerEstimate());
		}
		return acum.add(getAmountSpent());
	}

	// Upper end for the current estimated cost
	public BigDecimal getUpperEstimate() {
		BigDecimal acum = new BigDecimal(0);
		for(Task task : getAllTasks()) {
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

	public void addExpense(Expense expense) {
		expenses.add(expense);
	}

	protected enum EventType {
		CIVIL, CEREMONY, PARTY
	}

}
