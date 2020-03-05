package tm.arzuv.app.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import tm.arzuv.app.dao.DocumentViewModel;
import tm.arzuv.app.model.Document;

public interface DocumentService {
    public List<Document> findAll();
    public Document findById(int id);
    public Document findByName(String name);
    public Document save(Document d);
    public void delete(int id);
    public DocumentViewModel convertToDocumentViewModel(Document d);
    public Document convertToDocument(DocumentViewModel d);
}