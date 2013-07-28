package model.domain;

import java.math.BigDecimal;

public interface Budgetable extends Costable {
	// Amount to spend by the budgeteable
	public BigDecimal getBudget();
	//
	public BigDecimal getTotalCost();
	// Budget minus total cost
	public BigDecimal getAmountAvailable();
	// Costs already spent
	public BigDecimal getAmountSpent();
	// Amount that has to be paid in the future
	public BigDecimal getAmountComprised();
}
