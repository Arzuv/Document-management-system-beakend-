package tm.arzuv.app.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

	@Column(name="name_document")
	private String name_document;
	@Column(name="data_creat")
	private Date data_creat;
	@Column(name="status_document")
	private boolean status_document;
	@Column(name="file_document")
	private byte [] file_document;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getName_document() {
		return name_document;
	}
	public void setName_document(String name_document) {
		this.name_document = name_document;
	}
	public Date getData_creat() {
		return data_creat;
	}
	public void setData_creat(Date data_creat) {
		this.data_creat = data_creat;
	}
	public boolean isStatus_document() {
		return status_document;
	}
	public void setStatus_document(boolean status_document) {
		this.status_document = status_document;
	}
	public byte[] getFile_document() {
		return file_document;
	}
	public void setFile_document(byte[] file_document) {
		this.file_document = file_document;
	}
    
}