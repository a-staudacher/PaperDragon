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
@Table(name = "gameSession")
public class GameSession implements java.io.Serializable {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String name;
	
	@Column
	private String lore;
	
	@OneToOne(cascade = CascadeType.ALL)
	private DocumentModel picture;
	
	
    @OneToMany(mappedBy="gameSession", fetch=FetchType.EAGER)
    private Set<Chat> chatLines;
    
    @OneToMany(mappedBy="gameSession", fetch=FetchType.EAGER)
    private Set<User> users;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public GameSession(String name, String lore, DocumentModel picture) {
		super();
		this.name = name;
		this.lore = lore;
		this.picture = picture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Chat> getChatLines() {
		return chatLines;
	}

	public void setChatLines(Set<Chat> chatLines) {
		this.chatLines = chatLines;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
    
    public String getLore() {
		return lore;
	}

	public void setLore(String lore) {
		this.lore = lore;
	}
	
	

	public DocumentModel getPicture() {
		return picture;
	}

	public void setPicture(DocumentModel picture) {
		this.picture = picture;
	}

	public GameSession() {
    }

	public GameSession(String name, String lore, DocumentModel picture, Set<Chat> chatLines, Set<User> users) {
		super();
		this.name = name;
		this.lore = lore;
		this.picture = picture;
		this.chatLines = chatLines;
		this.users = users;
	}

	public GameSession(String name, String lore) {
		super();
		this.name = name;
		this.lore = lore;
	}
	
	




    



}