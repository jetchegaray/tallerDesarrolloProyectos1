package model.domain;

import java.math.BigDecimal;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@Entity("provider")
public class Provider {

	@Id public ObjectId id;

	private String name;
	private String address;
	private String mail;
	private ProviderType type;

	private String picture;
	private BigDecimal price;
	private String description;

	public enum ProviderType{
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
		},
		Otros{
			@Override
			public String toString() {
				return "Otros";
			}
		}
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

	public ProviderType getType() {
		return type;
	}

	public void setType(ProviderType type) {
		this.type = type;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
