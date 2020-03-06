package tm.arzuv.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import tm.arzuv.app.Service.DocumentService;
import tm.arzuv.app.dao.DocumentViewModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/document/")
public class DocumentsPageRestController {
    @Autowired
    private DocumentService documentService;

    @GetMapping(value="all")
    public ResponseEntity<List<DocumentViewModel>> getDocuments() {
        return documentService.findAll();
    }

    @GetMapping(value="all/{condition}")
    public ResponseEntity<List<DocumentViewModel>> getDocumentsAndSort(@PathVariable String condition) {
        return documentService.findAllAndSort(condition);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<DocumentViewModel> getDocumentById(@PathVariable String id) {
        return documentService.findById(id);
    }

    @GetMapping(value = "name/{name}")
    public ResponseEntity<DocumentViewModel> getDocumentByName(@PathVariable String name) {
        return documentService.findByName(name);
    }

    @PostMapping(value="create")
    public ResponseEntity<DocumentViewModel> createDocument(@RequestBody DocumentViewModel document) {
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping(value="update")
    public ResponseEntity<DocumentViewModel> updateDocument(@RequestBody DocumentViewModel document) {
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value="delete/{id}")
    public ResponseEntity<String> deleteDocument(@PathVariable String id) {
        return documentService.delete(id);
    }
}