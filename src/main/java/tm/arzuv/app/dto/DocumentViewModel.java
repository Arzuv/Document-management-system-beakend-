package tm.arzuv.app.dto;

import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import tm.arzuv.app.model.Document;
import tm.arzuv.app.model.Status;
import tm.arzuv.app.repository.DocumentRepository;
import tm.arzuv.app.repository.UserRepository;

@Getter @Setter @NoArgsConstructor
public class DocumentViewModel {
	@JsonIgnore
	private DocumentRepository documentRepository;
	public void init(DocumentRepository documentRepository) {
		this.documentRepository = documentRepository;
	}

    private Integer id;
    private UserViewModel author;
	private String name;
	private String whomContract;
	private String whoContracted;
	private Date termOfExecution;
	private String file;

	private Date created;
	private String status;

	public Document toDocument() {
		Document document = new Document();
		if (id != null) {
			document = documentRepository.findById(id.intValue());
		}
		document.setDname(name);
		document.setWhoContracted(whoContracted);
		document.setWhomContract(whomContract);
		document.setTermOfExecution(termOfExecution);
		document.setDfile(file);
		return document;
	}

	public static DocumentViewModel fromDocument(Document document) {
		DocumentViewModel d = new DocumentViewModel();
		d.setAuthor(UserViewModel.fromUser(document.getAuthor()));
		return getDocumentViewModel(document, d);
	}

	private static DocumentViewModel getDocumentViewModel(Document document, DocumentViewModel d) {
		d.setId(document.getId());
		d.setName(document.getDname());
		d.setWhoContracted(document.getWhoContracted());
		d.setWhomContract(document.getWhomContract());
		d.setTermOfExecution(document.getTermOfExecution());
		d.setFile(document.getDfile());
		d.setCreated(document.getCreated());
		d.setStatus(document.getStatus().name());
		return d;
	}

	public static DocumentViewModel fromDocumentWithoutAuthor(Document document) {
		DocumentViewModel d = new DocumentViewModel();
		return getDocumentViewModel(document, d);
	}
}
