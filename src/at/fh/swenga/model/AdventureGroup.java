package at.fh.swenga.model;
 
import java.util.HashSet;
import java.util.Set;
 
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import at.fh.swenga.model.DocumentModel;
 
@Entity
@Table(name = "adventureGroup")
public class AdventureGroup implements java.io.Serializable {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 30)
	private String name;
	
    @OneToMany(mappedBy="group", fetch=FetchType.LAZY)
    private Set<User> users;

	public AdventureGroup() {}

	public AdventureGroup(String name, Set<User> users) {
		super();
		this.name = name;
		this.users = users;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
 
	
	
}