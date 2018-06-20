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
@Table(name = "itemBase")
public class ItemBaseModel implements java.io.Serializable {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 20)
	private String name;
	
	@Column(nullable = false, length = 100)
	private String text;
	
	//todo: in documentation er diagramm
	@OneToOne(cascade = CascadeType.ALL)
	private DocumentModel picture;
	
    @OneToMany(mappedBy="itemBase", fetch=FetchType.LAZY)
    private Set<ItemModel> items;
	
	@Column(nullable = false)
	private int strength;

	@Column(nullable = false)
	private int dexterity;

	@Column(nullable = false)
	private int inteligenz;

	@Column(nullable = false)
	private int constitution;

	@Column(nullable = false)
	private int vitality;

	@Column(nullable = false)
	private int wisdom;

	@Column(nullable = false)
	private int charisma;

	@Column(nullable = false)
	private int price;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	private Rarity rarity;

	@ManyToOne
	private ItemType itemType;
	
	public ItemBaseModel() {}

	public ItemBaseModel(String name, String text, Set<ItemModel> items, int strength,
			int dexterity, int inteligenz, int constitution, int vitality, int wisdom, int charisma, int price,
			Rarity rarity, ItemType itemType) {
		super();
		this.name = name;
		this.text = text;
		this.items = items;
		this.strength = strength;
		this.dexterity = dexterity;
		this.inteligenz = inteligenz;
		this.constitution = constitution;
		this.vitality = vitality;
		this.wisdom = wisdom;
		this.charisma = charisma;
		this.price = price;
		this.rarity = rarity;
		this.itemType = itemType;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Set<ItemModel> getItems() {
		return items;
	}

	public void setItems(Set<ItemModel> items) {
		this.items = items;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getDexterity() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public int getInteligenz() {
		return inteligenz;
	}

	public void setInteligenz(int inteligenz) {
		this.inteligenz = inteligenz;
	}

	public int getConstitution() {
		return constitution;
	}

	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}

	public int getVitality() {
		return vitality;
	}

	public void setVitality(int vitality) {
		this.vitality = vitality;
	}

	public int getWisdom() {
		return wisdom;
	}

	public void setWisdom(int wisdom) {
		this.wisdom = wisdom;
	}

	public int getCharisma() {
		return charisma;
	}

	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public void setRarity(Rarity rarity) {
		this.rarity = rarity;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}
	
	
}