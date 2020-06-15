package tm.arzuv.app.Service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import org.w3c.dom.views.DocumentView;
import tm.arzuv.app.Service.DocumentService;
import tm.arzuv.app.dto.DocumentViewModel;
import tm.arzuv.app.model.Document;
import tm.arzuv.app.model.Status;
import tm.arzuv.app.model.User;
import tm.arzuv.app.repository.DocumentRepository;
import tm.arzuv.app.repository.UserRepository;

import java.util.Date;

@Service
public final class DocumentServiceImpl implements DocumentService {
	private DocumentRepository documentRepository;
    private UserRepository userRepository;
    private final int COUNT_PAGE=3;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository, UserRepository userRepository) {
        this.documentRepository = documentRepository;
        this.userRepository = userRepository;
    }

    @Override
	public ResponseEntity<Page<DocumentViewModel>> findAll(String name, Integer page, String sortBy) {
		Page<DocumentViewModel> result;
        Page<Document> documents;
        documents = documentRepository.findAllByDnameStartingWithIgnoreCase(name, PageRequest.of(page, COUNT_PAGE, Sort.Direction.ASC, sortBy));
        if (!documents.hasContent())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        result = documents.map(document->DocumentViewModel.fromDocument(document));

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<DocumentViewModel>> findDocumentsByAuthor(String name, Integer page, String sortBy) {
        Page<DocumentViewModel> result = null;
        try {
            Page<Document> documents = documentRepository.findByAuthor(getIdAuth(), name, 0, PageRequest.of(page.intValue(), COUNT_PAGE, Sort.Direction.ASC, sortBy));
            if (documents.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            result = documents.map(document-> DocumentViewModel.fromDocumentWithoutAuthor(document));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<DocumentViewModel>> findDocumentsByCreatedDate(Date createdDate, Integer page, String sortBy) {
        Page<DocumentViewModel> result = null;
        try {
            User author = userRepository.findById(getIdAuth().intValue());
            Page<Document> documents = documentRepository.findByAuthorAndCreatedAfterAndStatus(author, createdDate, Status.ACTIVE, PageRequest.of(page, COUNT_PAGE, Sort.Direction.ASC, sortBy));
            if (documents.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            result = documents.map(document-> DocumentViewModel.fromDocumentWithoutAuthor(document));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<DocumentViewModel>> findDocumentsByWhomContract(String whomContract, Integer page, String sortBy) {
        Page<DocumentViewModel> result = null;
        try {
            User author = userRepository.findById(getIdAuth().intValue());
            Page<Document> documents = documentRepository.findByAuthorAndWhomContractStartingWithIgnoreCaseAndStatus(author, whomContract, Status.ACTIVE, PageRequest.of(page, COUNT_PAGE, Sort.Direction.ASC, sortBy));
            if (documents.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            result = documents.map(document-> DocumentViewModel.fromDocumentWithoutAuthor(document));
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<DocumentViewModel>> findDocumentsByWhoContracted(String whoContracted, Integer page, String sortBy) {
        Page<DocumentViewModel> result = null;
        try {
            User author = userRepository.findById(getIdAuth().intValue());
            Page<Document> documents = documentRepository.findByAuthorAndWhoContractedStartingWithIgnoreCaseAndStatus(author, whoContracted, Status.ACTIVE, PageRequest.of(page, COUNT_PAGE, Sort.Direction.ASC, sortBy));
            if (documents.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            result = documents.map(document-> DocumentViewModel.fromDocumentWithoutAuthor(document));
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<DocumentViewModel>> findDocumentsByTermOfExecutionDate(Date termOfExecution, Integer page, String sortBy) {
        Page<DocumentViewModel> result = null;
        try {
            User author = userRepository.findById(getIdAuth().intValue());
            Date d = new Date(termOfExecution.getDay(), termOfExecution.getMonth(), termOfExecution.getYear());
            d.setHours(23);
            d.setMinutes(59);
            d.setSeconds(59);
            Page<Document> documents = documentRepository.findByAuthorAndTermOfExecutionBetweenAndStatus(author, termOfExecution, d, Status.ACTIVE, PageRequest.of(page, COUNT_PAGE, Sort.Direction.ASC, sortBy));
            if (documents.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            result = documents.map(document-> DocumentViewModel.fromDocumentWithoutAuthor(document));
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }

	@Override
	public ResponseEntity<DocumentViewModel> update(DocumentViewModel d) {
        try {
            d.init(documentRepository);
            Document document = d.toDocument();
            document = documentRepository.save(document);
            return new ResponseEntity<>(DocumentViewModel.fromDocument(document), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
	}

    @Override
    public ResponseEntity<DocumentViewModel> create(DocumentViewModel d) {
        try {
            Document document = d.toDocument();
            document.setAuthor(userRepository.findById(getIdAuth().intValue()));
            document.setStatus(Status.ACTIVE);
            document = documentRepository.save(document);
            return new ResponseEntity<>(DocumentViewModel.fromDocument(document), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
	public ResponseEntity delete(Integer id) {
        try {
            Document document = documentRepository.findById((int)id);
            if (document.getAuthor().getId() != getIdAuth())
                return new ResponseEntity<>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);

            document.setStatus(Status.DELETED);
            documentRepository.save(document);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
	}

    public Integer getIdAuth() {
	    String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmailAndStatus(email, Status.ACTIVE).getId();
    }
}
