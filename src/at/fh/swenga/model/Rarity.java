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
@Table(name = "rarity")
public class Rarity implements java.io.Serializable {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 20)
	private String name;
	
    @OneToMany(mappedBy="rarity", fetch=FetchType.LAZY)
    private Set<ItemBaseModel> itemBases;

	public int getId() {
		return id;
	}
 
	public void setId(int id) {
		this.id = id;
	}

	public Rarity() {}

	public Rarity(String name, Set<ItemBaseModel> itemBases) {
		super();
		this.name = name;
		this.itemBases = itemBases;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ItemBaseModel> getItemBases() {
		return itemBases;
	}

	public void setItemBases(Set<ItemBaseModel> itemBases) {
		this.itemBases = itemBases;
	}
	
	
}