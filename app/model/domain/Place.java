package model.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Place {

	public Provider provider;
	public BigDecimal price;
	public Location location;
	public List<Date> schedules;
}
