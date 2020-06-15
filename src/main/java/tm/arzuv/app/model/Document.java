package tm.arzuv.app.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="documents")
@Getter @Setter @NoArgsConstructor
public class Document extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
	private User author;
	
	@Column(name="dname")
	private String dname;

	@Column(name="whomcontract")
	private String whomContract;

	@Column(name="whocontracted")
	private String whoContracted;

	@Column(name="termofexecution")
	private Date termOfExecution;

	@Column(name="dfile")
	private String dfile;
}
