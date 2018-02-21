package pl.twitter.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "message")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private long id;

	private String text;

	private Date created;

	@ManyToOne
	private User sender;

	@ManyToOne
	private User reciever;

	private int checked;

	public Message() {
		super();
		this.created = new Date();
		this.checked = 0;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReciever() {
		return reciever;
	}

	public void setReciever(User reciever) {
		this.reciever = reciever;
	}

	public int getChecked() {
		return checked;
	}

	public void setChecked(int checked) {
		this.checked = checked;
	}

}