package at.fh.swenga.model;
 
import java.util.HashSet;
import java.util.Set;
import java.util.Date;
 
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import at.fh.swenga.model.DocumentModel;
 
@Entity
@Table(name = "posts")
public class Post implements java.io.Serializable {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String text;
	
	@Column(nullable = false)
	private int number;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	private User users;

	@ManyToOne (cascade = CascadeType.PERSIST)
	private Threat threats;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public Threat getThreats() {
		return threats;
	}

	public void setThreats(Threat threats) {
		this.threats = threats;
	}
    
    public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Post() {
    	
    }

	public Post(String text, int number, User users, Threat threats, Date date) {
		super();
		this.text = text;
		this.number = number;
		this.users = users;
		this.threats = threats;
		this.date = date;
	}


    


}