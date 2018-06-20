package at.fh.swenga.model;
 
import java.util.Date;
 
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
 
@Entity
@Table(name = "Document")
public class DocumentModel implements java.io.Serializable {
 
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
 
	private String name;
	private String description;
	private String filename;
 
	// other possibilities but byte[] definitly recommended, also keep fetch type lazy!!
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] content;
 
	private String contentType;
	private Date created;
	
	@OneToOne(mappedBy="picture")
	private User user;
	
	@OneToOne(mappedBy="picture")
	private GameSession gameSession;
 
	@Version
	long version;
 
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
 
	public String getDescription() {
		return description;
	}
 
	public void setDescription(String description) {
		this.description = description;
	}
 
	public String getFilename() {
		return filename;
	}
 
	public void setFilename(String filename) {
		this.filename = filename;
	}
 
	public byte[] getContent() {
		return content;
	}
 
	public void setContent(byte[] content) {
		this.content = content;
	}
 
	public String getContentType() {
		return contentType;
	}
 
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
 
	public Date getCreated() {
		return created;
	}
 
	public void setCreated(Date created) {
		this.created = created;
	}
 
}