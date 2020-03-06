package tm.arzuv.app.Service;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import tm.arzuv.app.dao.DocumentViewModel;
import tm.arzuv.app.model.Document;

public interface DocumentService {
    public ResponseEntity<List<DocumentViewModel>> findAll();
    public ResponseEntity<List<DocumentViewModel>> findAllAndSort(String condition);
    public ResponseEntity<DocumentViewModel> findById(String id);
    public ResponseEntity<DocumentViewModel> findByName(String name);
    public ResponseEntity<List<DocumentViewModel>> findByWhomContract(String whomContract);
    public ResponseEntity<List<DocumentViewModel>> findByWhoContracted(String whoContracted);
    public ResponseEntity<DocumentViewModel> save(Document d);
    public ResponseEntity<String> delete(String id);
    public DocumentViewModel convertToDocumentViewModel(Document d);
    public Document convertToDocument(DocumentViewModel d);
}