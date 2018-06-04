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
@Table(name = "threats")
public class Threat implements java.io.Serializable {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String titel;
	
    @OneToMany(mappedBy="threats", fetch=FetchType.LAZY)
    private Set<Post> posts;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}



	public Threat() {
		
	}

	public Threat(String titel, Set<Post> posts) {
		super();
		this.titel = titel;
		this.posts = posts;
	}

	


    
}