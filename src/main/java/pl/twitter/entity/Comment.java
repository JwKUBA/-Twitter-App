package pl.twitter.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	@Size(max=60)
	private String text;

	@ManyToOne
	private User user;

	@ManyToOne
	private Tweet tweet;

	private Date created;

	public Comment() {
		super();
		this.created = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Tweet getTweet() {
		return tweet;
	}

	public void setTweet(Tweet tweet) {
		this.tweet = tweet;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}