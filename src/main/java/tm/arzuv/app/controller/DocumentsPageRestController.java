package tm.arzuv.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tm.arzuv.app.Service.DocumentService;
import tm.arzuv.app.dto.DocumentViewModel;

@RestController
@RequestMapping("/api/document/")
@CrossOrigin
public class DocumentsPageRestController {
    private DocumentService documentService;

    @Autowired
    public DocumentsPageRestController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping(value = "all")
    public ResponseEntity<Page<DocumentViewModel>> getDocumentByAuthor(
            @RequestParam Optional<String> name,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy) {
        return documentService.findDocumentsByAuthor(name.orElse(""), page.orElse(0), sortBy.orElse("id"));
    }

    @GetMapping(value = "all/filter/dateCreated")
    public ResponseEntity<Page<DocumentViewModel>> getDocumentByDateCreated(
            @RequestParam Optional<String> createdDate,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy)
    {
        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");

        try {
            Date d = s.parse(createdDate.get());
            return documentService.findDocumentsByCreatedDate(d, page.orElse(0), sortBy.orElse("id"));
        } catch (ParseException e) {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "all/filter/whoContracted")
    public ResponseEntity<Page<DocumentViewModel>> getDocumentByWhoContracted(
            @RequestParam Optional<String> whoContracted,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy)
    {
        return documentService.findDocumentsByWhoContracted(whoContracted.get(), page.orElse(0), sortBy.orElse("id"));
    }

    @GetMapping(value = "all/filter/whomContract")
    public ResponseEntity<Page<DocumentViewModel>> getDocumentByWhomContract(
            @RequestParam Optional<String> whomContract,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy)
    {
        return documentService.findDocumentsByWhomContract(whomContract.get(), page.orElse(0), sortBy.orElse("id"));
    }

    @GetMapping(value = "all/filter/dateContract")
    public ResponseEntity<Page<DocumentViewModel>> getDocumentByDateContract(
            @RequestParam Optional<String> dateContract,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy)
    {
        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");

        try {
            Date d = s.parse(dateContract.get());
            return documentService.findDocumentsByTermOfExecutionDate(d, page.orElse(0), sortBy.orElse("id"));
        } catch (ParseException e) {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value="create")
    public ResponseEntity<DocumentViewModel> createDocument(@RequestBody DocumentViewModel document) {
        return documentService.create(document);
    }

    @PutMapping(value="update")
    public ResponseEntity<DocumentViewModel> updateDocument(@RequestBody DocumentViewModel document) {
        return documentService.update(document);
    }

    @DeleteMapping(value="delete")
    public ResponseEntity<String> deleteDocument(@RequestParam Integer id) {
        return documentService.delete(id);
    }
}
