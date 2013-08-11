package model.domain;

import java.util.Date;
import java.math.BigDecimal;

import com.google.code.morphia.annotations.Transient;
import com.google.code.morphia.annotations.Converters;
import extensions.morphia.BigDecimalConverter;

import play.data.validation.Constraints.Required;

@Converters({BigDecimalConverter.class})
public class Expense {
	@Required public String reason;
	@Required public BigDecimal total;
	public String eventOrigin;
	public BigDecimal paid;
	public Date date;

	public Expense() {
		this.paid = new BigDecimal(0);
		this.date = new Date();
	}
	public Expense(String reason, BigDecimal total) {
		this();
		this.reason = reason;
		this.total = total;
	}

	public BigDecimal getTotal() { return total; }
	// Costs already spent
	public BigDecimal getAmountSpent() { return paid; }
	// Amount that has to be paid in the future
	public BigDecimal getAmountComprised() { return total.subtract(paid); }

	public static class CustomHire extends Expense {
		public String description;
		public String name;
		public String email;

		public CustomHire() { super(); }

		public CustomHire(Task task, BigDecimal total) {
			super(task.name, total);
			this.eventOrigin = task.eventType;
			this.paid = total;
		}
	}
}
