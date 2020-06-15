package tm.arzuv.app.Service;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import tm.arzuv.app.dto.DocumentViewModel;

import java.util.Date;

public interface DocumentService {
    public ResponseEntity<Page<DocumentViewModel>> findAll(String name, Integer page, String sortBy);
    public ResponseEntity<Page<DocumentViewModel>> findDocumentsByAuthor(String name, Integer page, String sortBy);
    public ResponseEntity<Page<DocumentViewModel>> findDocumentsByCreatedDate(Date createdDate, Integer page, String sortBy);
    public ResponseEntity<Page<DocumentViewModel>> findDocumentsByWhomContract(String whomContract, Integer page, String sortBy);
    public ResponseEntity<Page<DocumentViewModel>> findDocumentsByWhoContracted(String whoContracted, Integer page, String sortBy);
    public ResponseEntity<Page<DocumentViewModel>> findDocumentsByTermOfExecutionDate(Date termOfExecution, Integer page, String sortBy);
    public ResponseEntity<DocumentViewModel> update(DocumentViewModel d);
    public ResponseEntity<DocumentViewModel> create(DocumentViewModel d);
    public ResponseEntity delete(Integer id);
}
