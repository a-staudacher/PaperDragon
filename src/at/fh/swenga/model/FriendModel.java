package at.fh.swenga.model;
 
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
 
@Entity
@Table(name = "friendList")
public class FriendModel implements java.io.Serializable {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private User friend1;
	
	@ManyToOne
	private User friend2;
	
	@Column(nullable = false)
	private boolean accepted;
	
	public FriendModel() {}
	
	
	public FriendModel(User friend1, User friend2, boolean accepted) {
		super();
		this.friend1 = friend1;
		this.friend2 = friend2;
		this.accepted = accepted;
	}
 
 
	public User getFriend1() {
		return friend1;
	}


	public void setFriend1(User friend1) {
		this.friend1 = friend1;
	}


	public User getFriend2() {
		return friend2;
	}


	public void setFriend2(User friend2) {
		this.friend2 = friend2;
	}


	public int getId() {
		return id;
	}
 
	public void setId(int id) {
		this.id = id;
	}


	public boolean isAccepted() {
		return accepted;
	}


	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	
	

}