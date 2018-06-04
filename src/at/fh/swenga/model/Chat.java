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
@Table(name = "chatLines")
public class Chat implements java.io.Serializable {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	private User user;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	private GameSession gameSession;
	    
	@Column(nullable = false)
	private String text;
	
	@Temporal(TemporalType.DATE)
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public GameSession getGamesession() {
		return gameSession;
	}

	public void setGamesession(GameSession gamesession) {
		this.gameSession = gamesession;
	}
	
	public Chat() {
		
	}
	
	public Chat(User user, GameSession gameSession, String text, Date date) {
		super();
		this.user = user;
		this.gameSession = gameSession;
		this.text = text;
		this.date = date;
	}
	

	
	

}