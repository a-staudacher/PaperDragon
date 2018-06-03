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
@Table(name = "itemType")
public class ItemType implements java.io.Serializable {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 20)
	private String type;
	
    @OneToMany(mappedBy="itemType", fetch=FetchType.LAZY)
    private Set<ItemBaseModel> itemBases;

	public int getId() {
		return id;
	}
 
	public void setId(int id) {
		this.id = id;
	}
	
	public ItemType() {}

	public ItemType(String type, Set<ItemBaseModel> itemBases) {
		super();
		this.type = type;
		this.itemBases = itemBases;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<ItemBaseModel> getItemBases() {
		return itemBases;
	}

	public void setItemBases(Set<ItemBaseModel> itemBases) {
		this.itemBases = itemBases;
	}
	
	

}