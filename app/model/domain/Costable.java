package model.domain;

import java.math.BigDecimal;

public interface Costable {
	// Lower end for the current estimated cost
	public BigDecimal getLowerEstimate();
	// Upper end for the current estimated cost
	public BigDecimal getUpperEstimate();
}
