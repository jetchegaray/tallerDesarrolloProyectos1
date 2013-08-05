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
	public BigDecimal paid;
	public Date date;
	@Transient String type;

	public Expense() {
		this.paid = new BigDecimal(0);
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

}
