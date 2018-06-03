package at.fh.swenga.model;
 
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
 
@Entity
@Table(name = "blockList")
public class BlockModel implements java.io.Serializable {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	private User user;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	private User blockedUser;
	
	public BlockModel() {}
	
	
	public BlockModel(User user, User blockedUser) {
		super();
		this.user = user;
		this.blockedUser = blockedUser;
	}
 
 
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	public User getBlockedUser() {
		return blockedUser;
	}


	public void setBlockedUser(User blockedUser) {
		this.blockedUser = blockedUser;
	}


	public int getId() {
		return id;
	}
 
	public void setId(int id) {
		this.id = id;
	}

}