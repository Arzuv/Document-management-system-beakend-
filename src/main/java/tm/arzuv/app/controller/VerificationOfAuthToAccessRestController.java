package tm.arzuv.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/")
@CrossOrigin
public class VerificationOfAuthToAccessRestController {
    @GetMapping("verification/auth")
    public ResponseEntity checkAuth() {
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("admin/verification")
    public ResponseEntity checkAdmin() {
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("document/verification")
    public ResponseEntity checkUser() {
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
