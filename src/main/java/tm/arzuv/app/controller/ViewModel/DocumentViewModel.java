package tm.arzuv.app.controller.ViewModel;

import java.util.Date;

public class DocumentViewModel {
    
    private int id;

    private UserViewModel userViewModel;

	private String name_document;
	
	private Date data_creat;
	
	private boolean status_document;
	
	private String file_document;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserViewModel getUser() {
		return userViewModel;
	}
	public void setUser(UserViewModel user) {
		this.userViewModel = user;
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
	public String getFile_document() {
		return file_document;
	}
	public void setFile_document(String file_document) {
		this.file_document = file_document;
	}
}