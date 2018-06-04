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
 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import at.fh.swenga.model.DocumentModel;
 
@Entity
@Table(name = "users")
public class User implements java.io.Serializable {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
 
	@ManyToOne (cascade = CascadeType.PERSIST)
	private AccountStatus accountStatus;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	private AdventureGroup group;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	private GameSession gameSession;
 
	@Column(name = "username", unique = true, nullable = false, length = 45)
	private String userName;
 
	@Column(name = "password", nullable = false, length = 60)
	private String password;
 
	@Column(name = "enabled", nullable = false)
	private boolean enabled;
 
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.PERSIST)
	private Set<UserRole> userRoles;
	
    @OneToMany(mappedBy="friend1", fetch=FetchType.LAZY)
    private Set<FriendModel> friends1;
    
    @OneToMany(mappedBy="friend2", fetch=FetchType.LAZY)
    private Set<FriendModel> friends2;
    
    @OneToMany(mappedBy="user", fetch=FetchType.LAZY)
    private Set<BlockModel> block1;
    
    @OneToMany(mappedBy="blockedUser", fetch=FetchType.LAZY)
    private Set<BlockModel> blockedUsers;
    
	@OneToOne(cascade = CascadeType.ALL)
	private DocumentModel picture;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Character character;
	
    @OneToMany(mappedBy="user", fetch=FetchType.LAZY)
    private Set<Chat> chatLines;
    
    @OneToMany(mappedBy="users", fetch=FetchType.LAZY)
    private Set<Post> posts;
 
	public User() {
	}
 
	public User(String userName, String password, boolean enabled) {
		this.userName = userName;
		this.password = password;
		this.enabled = enabled;
	} 
 
	




	public User(AccountStatus accountStatus, AdventureGroup group, GameSession gameSession, String userName,
			String password, boolean enabled, Set<UserRole> userRoles, Set<FriendModel> friends1,
			Set<FriendModel> friends2, Set<BlockModel> block1, Set<BlockModel> blockedUsers, DocumentModel picture,
			Character character, Set<Chat> chatLines, Set<Post> posts) {
		super();
		this.accountStatus = accountStatus;
		this.group = group;
		this.gameSession = gameSession;
		this.userName = userName;
		this.password = password;
		this.enabled = enabled;
		this.userRoles = userRoles;
		this.friends1 = friends1;
		this.friends2 = friends2;
		this.block1 = block1;
		this.blockedUsers = blockedUsers;
		this.picture = picture;
		this.character = character;
		this.chatLines = chatLines;
		this.posts = posts;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public int getId() {
		return id;
	}
 
	public void setId(int id) {
		this.id = id;
	}
 
	public String getUserName() {
		return userName;
	}
 
	public void setUserName(String userName) {
		this.userName = userName;
	}
 
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
 
	public boolean isEnabled() {
		return enabled;
	}
 
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	} 

	public DocumentModel getPicture() {
		return picture;
	}

	public void setPicture(DocumentModel picture) {
		this.picture = picture;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
 
	public void addUserRole(UserRole userRole) {
		if (userRoles==null) userRoles = new HashSet<UserRole>();
		userRoles.add(userRole);
	}

	public void addFriend(User user) {
		if (friends1==null) friends1 = new HashSet<FriendModel>();
		friends1.add(new FriendModel(this, user));
		//todo: persist FriendModel
	}

	public void addBlockedUser(User user) {
		if (blockedUsers==null) blockedUsers = new HashSet<BlockModel>();
		blockedUsers.add(new BlockModel(this, user));
		//todo: persist BlockModel
	}
	
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}
 
	public void encryptPassword() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		password = passwordEncoder.encode(password);		
	}

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}

	public AdventureGroup getGroup() {
		return group;
	}

	public void setGroup(AdventureGroup group) {
		this.group = group;
	}

	public Set<FriendModel> getFriends1() {
		return friends1;
	}

	public void setFriends1(Set<FriendModel> friends1) {
		this.friends1 = friends1;
	}

	public Set<FriendModel> getFriends2() {
		return friends2;
	}

	public void setFriends2(Set<FriendModel> friends2) {
		this.friends2 = friends2;
	}

	public Set<BlockModel> getBlock1() {
		return block1;
	}

	public void setBlock1(Set<BlockModel> block1) {
		this.block1 = block1;
	}

	public Set<BlockModel> getBlockedUsers() {
		return blockedUsers;
	}

	public void setBlockedUsers(Set<BlockModel> blockedUsers) {
		this.blockedUsers = blockedUsers;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public GameSession getGameSession() {
		return gameSession;
	}

	public void setGameSession(GameSession gameSession) {
		this.gameSession = gameSession;
	}

	public Set<Chat> getChatLines() {
		return chatLines;
	}

	public void setChatLines(Set<Chat> chatLines) {
		this.chatLines = chatLines;
	}
	
	
 
}