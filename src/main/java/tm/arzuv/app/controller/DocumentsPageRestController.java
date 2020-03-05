package tm.arzuv.app.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import tm.arzuv.app.Service.DocumentService;
import tm.arzuv.app.dao.DocumentViewModel;
import tm.arzuv.app.repository.DocumentRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/documents/")
public class DocumentsPageRestController {
    
    // @Autowired
    // DocumentRepository documentRepository;

    // @Autowired
    // DocumentService documentService;

    // @GetMapping("all")
    // @ResponseBody
    // public ResponseEntity<List<DocumentViewModel>>cuments() {
    //     List<DocumentViewModel> result = documentRepository.findAll().stream()
    //                                 .map(document -> documentService.convertToDocumentViewModel(document))
    //                                 .collect(Collectors.toList());
    //     if (result == null)
    //         return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    //     return new ResponseEntity<>(result, HttpStatus.OK);
    // }
}