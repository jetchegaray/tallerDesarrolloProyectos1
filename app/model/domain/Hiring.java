package model.domain;

import java.math.BigDecimal;
import java.util.Map;

import org.bson.types.ObjectId;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.stereotype.Component;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;
import com.google.common.collect.Maps;

@Entity("hirings")
public class Hiring {

	@Id
	ObjectId id;
	private String name;
	private String address;
	private String mail;
	private HiringType type;
	
	private Conversation conversation;
	private String picture;
	private BigDecimal price;
	
	public enum HiringType{
		PHOTOS{
			@Override
			public String toString() {
				return "Fotos";
			}
		},
		PLACE{
			@Override
			public String toString() {
				return "Lugar";
			}
		},
		VIDEOS{
			@Override
			public String toString() {
				return "Videos";
			}
		},
		MUSIC{
			@Override
			public String toString() {
				return "Musica";
			}
		}
	}
	private static class Conversation{
		private static final String KEY_USER = "USER";
		private static final String HIRE_USER = "HIRE";
		
		private Map<String, String> messages = Maps.newLinkedHashMap();
	}

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

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
	
	public void addConversationMessageUser(String message) {
		 conversation.messages.put(Conversation.KEY_USER, message);
	}

	public void addConversationMessageHireUser(String message) {
		conversation.messages.put(Conversation.HIRE_USER, message);
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
	
	
	
	
	
}
