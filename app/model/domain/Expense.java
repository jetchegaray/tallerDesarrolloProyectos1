package model.domain;

import java.util.Date;
import java.math.BigDecimal;

public class Expense {
	BigDecimal total;
	BigDecimal paid;
	String reason;

	Expense() {}
	public Expense(String reason, BigDecimal total) {
		this.reason = reason;
		this.total = total;
		this.paid = new BigDecimal(0);
	}

	public BigDecimal getTotal() { return total; }
	// Costs already spent
	public BigDecimal getAmountSpent() { return paid; }
	// Amount that has to be paid in the future
	public BigDecimal getAmountComprised() { return total.subtract(paid); }

}
