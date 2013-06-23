package model.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Wedding {
	
	public List<Guest> husbandGuests;
	public List<Guest> wifeGuests;
	public List<Present> presents;
	
	public Place place;
	
	public WeddingType type;
	Calendar calendar;
	Date date;
	
	
	private enum WeddingType{
		CHURCH,CIVIL
	}
}
