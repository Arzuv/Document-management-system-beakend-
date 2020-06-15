package tm.arzuv.app.controller;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tm.arzuv.app.Service.DocumentService;
import tm.arzuv.app.Service.UserService;
import tm.arzuv.app.dto.DocumentViewModel;
import tm.arzuv.app.dto.UserViewModel;

@RestController
@CrossOrigin
@RequestMapping("/api/admin/")
public class AdminRestController {
    private DocumentService documentService;
    private UserService userService;

    @Autowired
    public AdminRestController(DocumentService documentService, UserService userService) {
        this.documentService = documentService;
        this.userService = userService;
    }

    @GetMapping(value="/user/all")
    public ResponseEntity<Page<UserViewModel>> getUsers(@RequestParam Optional<Integer> page,
                        @RequestParam Optional<String> sortBy) {
        return userService.findAll(page.orElse(0), sortBy.orElse("id"));
    }

    @GetMapping(value="/document/all")
    public ResponseEntity<Page<DocumentViewModel>> getDocuments(
            @RequestParam Optional<String> name,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy) {
        return documentService.findAll(name.orElse(""), page.orElse(0), sortBy.orElse("id"));
    }
}
