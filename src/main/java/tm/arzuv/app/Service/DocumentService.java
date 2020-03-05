package tm.arzuv.app.Service;

import java.util.Date;
import java.util.List;

import tm.arzuv.app.dao.DocumentViewModel;
import tm.arzuv.app.model.Document;

public interface DocumentService {
    public List<Document> findAll();
    public List<Document> findAllAndSort(String condition);
    public Document findById(int id);
    public Document findByName(String name);
    public List<Document> findByWhomContract(String whomContract);
    public List<Document> findByWhoContracted(String whoContracted);
    public Document save(Document d);
    public void delete(int id);
    public DocumentViewModel convertToDocumentViewModel(Document d);
    public Document convertToDocument(DocumentViewModel d);
}