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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import at.fh.swenga.model.DocumentModel;
 
@Entity
@Table(name = "itemModel")
public class ItemModel implements java.io.Serializable {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private int number;
	
	@Column(nullable = false)
	private boolean equipped;
	
	@ManyToOne
	private Character character;

	@ManyToOne
	private ItemBaseModel itemBase;
	
	public ItemModel() {}

	public ItemModel(int number, boolean equipped, Character character, ItemBaseModel itemBase) {
		super();
		this.number = number;
		this.equipped = equipped;
		this.character = character;
		this.itemBase = itemBase;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isEquipped() {
		return equipped;
	}

	public void setEquipped(boolean equipped) {
		this.equipped = equipped;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public ItemBaseModel getItemBase() {
		return itemBase;
	}

	public void setItemBase(ItemBaseModel itemBase) {
		this.itemBase = itemBase;
	}
	
	
}