package tm.arzuv.app.dao;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class DocumentViewModel {
    private int id;
    private UserViewModel author;
	private String name;
	private String whomContract;
	private String whoContracted;
	private Date termOfExecution;
	private byte [] file;
	private Date created;	
	private boolean status;
}