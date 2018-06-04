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
@Table(name = "PlayerCharacter")
public class Character implements java.io.Serializable {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 30)
	private String name;
	
	@Column(nullable = false, length = 200)
	private String history;

	@Column(nullable = false)
	private Integer strength;
	
	@Column(nullable = false)
	private Integer intelligenz;
	
	@Column(nullable = false)
	private Integer dexterity;
	
	@Column(nullable = false)
	private Integer constitution;
	
	@Column(nullable = false)
	private Integer vitality;
	
	@Column(nullable = false)
	private Integer wisdom;
	
	@Column(nullable = false)
	private Integer charisma;
	
	@Column(nullable = false, length = 1)
	private String gender;
	
    @OneToMany(mappedBy="character", fetch=FetchType.LAZY)
    private Set<ItemModel> items;
    
    public Character() {}

	public Character(String name, String history, Integer strength, Integer intelligenz, Integer dexterity,
			Integer constitution, Integer vitality, Integer wisdom, Integer charisma, String gender,
			Set<ItemModel> items) {
		super();
		this.name = name;
		this.history = history;
		this.strength = strength;
		this.intelligenz = intelligenz;
		this.dexterity = dexterity;
		this.constitution = constitution;
		this.vitality = vitality;
		this.wisdom = wisdom;
		this.charisma = charisma;
		this.gender = gender;
		this.items = items;
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

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public Integer getStrength() {
		return strength;
	}

	public void setStrength(Integer strength) {
		this.strength = strength;
	}

	public Integer getIntelligenz() {
		return intelligenz;
	}

	public void setIntelligenz(Integer intelligenz) {
		this.intelligenz = intelligenz;
	}

	public Integer getDexterity() {
		return dexterity;
	}

	public void setDexterity(Integer dexterity) {
		this.dexterity = dexterity;
	}

	public Integer getConstitution() {
		return constitution;
	}

	public void setConstitution(Integer constitution) {
		this.constitution = constitution;
	}

	public Integer getVitality() {
		return vitality;
	}

	public void setVitality(Integer vitality) {
		this.vitality = vitality;
	}

	public Integer getWisdom() {
		return wisdom;
	}

	public void setWisdom(Integer wisdom) {
		this.wisdom = wisdom;
	}

	public Integer getCharisma() {
		return charisma;
	}

	public void setCharisma(Integer charisma) {
		this.charisma = charisma;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Set<ItemModel> getItems() {
		return items;
	}

	public void setItems(Set<ItemModel> items) {
		this.items = items;
	}
    
    

}