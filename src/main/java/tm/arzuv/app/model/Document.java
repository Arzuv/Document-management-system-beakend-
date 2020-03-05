package tm.arzuv.app.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity(name="documents")
@Data
public class Document extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
	private User author;
	
	@Column(name="dname")
	private String dname;

	@Column(name="whomContract")
	private String whomContract;

	@Column(name="whoContracted")
	private String whoContracted;

	@Column(name="termOfExecution")
	private Date termOfExecution;

	@Column(name="dfile")
	private byte [] dfile;
}