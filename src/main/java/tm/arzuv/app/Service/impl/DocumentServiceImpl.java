package tm.arzuv.app.Service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tm.arzuv.app.Service.DocumentService;
import tm.arzuv.app.Service.UserService;
import tm.arzuv.app.dao.DocumentViewModel;
import tm.arzuv.app.model.Document;
import tm.arzuv.app.model.Status;
import tm.arzuv.app.repository.DocumentRepository;

@Service
public final class DocumentServiceImpl implements DocumentService {
    @Autowired
	private DocumentRepository documentRepository;
	@Autowired
	private UserService userService;

	@Override
	public ResponseEntity<List<DocumentViewModel>> findAll() {
		List<DocumentViewModel> result = new ArrayList<>();
        List<Document> documents = documentRepository.findAll();
        if (documents.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            
        result = documents.stream()
                .map(document->convertToDocumentViewModel(document))
                .collect(Collectors.toList());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @Override
	public ResponseEntity<List<DocumentViewModel>> findAllAndSort(String condition) {
		List<DocumentViewModel> result = new ArrayList<>();
        List<Document> documents = documentRepository.findAll(Sort.by(condition));
        if (documents.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        result = documents.stream()
            .map(document->convertToDocumentViewModel(document))
            .collect(Collectors.toList());

        return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<DocumentViewModel> findById(String id) {
		DocumentViewModel result=null;
        try {
            Document document = documentRepository.findById(Integer.parseInt(id));
            if (document == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            result = convertToDocumentViewModel(document);   
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<DocumentViewModel> findByName(String name) {
		Document document = documentRepository.findByDname(name);
        if (document == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        DocumentViewModel result = convertToDocumentViewModel(document);

        return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<DocumentViewModel>> findByWhomContract(String whomContract) {
		List<DocumentViewModel> result = new ArrayList<>();
        List<Document> documents = documentRepository.findByWhomContract(whomContract);
        if (documents.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            
        result = documents.stream()
                .map(document->convertToDocumentViewModel(document))
                .collect(Collectors.toList());

        return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<DocumentViewModel>> findByWhoContracted(String whoContracted) {
		List<DocumentViewModel> result = new ArrayList<>();
        List<Document> documents = documentRepository.findByWhoContracted(whoContracted);
        if (documents.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            
        result = documents.stream()
                .map(document->convertToDocumentViewModel(document))
                .collect(Collectors.toList());

        return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<DocumentViewModel> save(Document d) {
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<String> delete(String id) {
		try {
			if (documentRepository.findById(Integer.parseInt(id))==null)
				return new ResponseEntity<>("false", HttpStatus.NOT_FOUND);
            documentRepository.deleteById(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("true", HttpStatus.OK);
	}
	
    @Override
    public DocumentViewModel convertToDocumentViewModel(Document d) {
		DocumentViewModel document = new DocumentViewModel();
		document.setId(d.getId());
		document.setAuthor(userService.convertToUserViewModel(d.getAuthor()));
		document.setName(d.getDname());
		document.setCreated(d.getCreated());
		document.setStatus(d.getStatus().equals(Status.ACTIVE));
		document.setWhoContracted(d.getWhoContracted());
		document.setWhomContract(d.getWhomContract());
		document.setFile(d.getDfile());
		return document;
    }
    
    @Override
    public Document convertToDocument(DocumentViewModel d) {
        Document document = new Document();
        if (d.getId() != 0)
            document.setId(d.getId());
        document.setDname(d.getName());
        document.setWhoContracted(d.getWhoContracted());
        document.setWhomContract(d.getWhomContract());
        document.setTermOfExecution(d.getTermOfExecution());
        document.setDfile(d.getFile());
        return document;
    }
}