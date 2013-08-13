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

	@Id public ObjectId id;

	@Reference public Provider provider;
	@Reference public Wedding wedding;
	public String eventType;
	public String taskSlug;

	private List<String> userMessages = Lists.newArrayList();
	private List<String> hiredMessages = Lists.newArrayList();

	public Task getTask() {
		return wedding.getEvent(eventType).findTaskBySlug(taskSlug);
	}

	public String getName() {
		return provider.getName();
	}

	public String getAddress() {
		return provider.getAddress();
	}

	public String getMail() {
		return provider.getMail();
	}

	public Provider.ProviderType getType() {
		return provider.getType();
	}

	public void addConversationMessageUser(String message) {
		 userMessages.add(message);
	}

	public void addConversationMessageHireUser(String message) {
		hiredMessages.add(message);
	}


	public String getDescription() {
		return provider.getDescription();
	}

	public String getPicture() {
		return provider.getPicture();
	}

	public BigDecimal getPrice() {
		return provider.getPrice();
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
