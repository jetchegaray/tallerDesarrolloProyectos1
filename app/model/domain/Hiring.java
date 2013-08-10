package model.domain;

import java.math.BigDecimal;
import java.util.List;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.annotations.Reference;
import com.google.common.collect.Lists;

@Entity("hirings")
public class Hiring {

	@Id
	public ObjectId id;
	
	private String name;
	private String address;
	private String mail;
	private HiringType type;
	
	private String picture;
	private BigDecimal price;
	
	public enum HiringType{
		Fotos{
			@Override
			public String toString() {
				return "Fotos";
			}
		},
		Lugares{
			@Override
			public String toString() {
				return "Lugares";
			}
		},
		Videos{
			@Override
			public String toString() {
				return "Videos";
			}
		},
		Musica{
			@Override
			public String toString() {
				return "Musica";
			}
		}
	}
	
	private List<String> userMessages = Lists.newArrayList();
	private List<String> hiredMessages = Lists.newArrayList();
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public HiringType getType() {
		return type;
	}

	public void setType(HiringType type) {
		this.type = type;
	}

	
	public void addConversationMessageUser(String message) {
		 userMessages.add(message);
	}

	public void addConversationMessageHireUser(String message) {
		hiredMessages.add(message);
	}
	

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<String> getUserMessages() {
		return userMessages;
	}

	public void setUserMessages(List<String> userMessages) {
		this.userMessages = userMessages;
	}

	public List<String> getHiredMessages() {
		return hiredMessages;
	}

	public void setHiredMessages(List<String> hiredMessages) {
		this.hiredMessages = hiredMessages;
	}
	
}
