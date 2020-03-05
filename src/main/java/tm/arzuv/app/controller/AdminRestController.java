package tm.arzuv.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import tm.arzuv.app.Service.UserService;
import tm.arzuv.app.dao.UserViewModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/admin/")
public class AdminRestController {

    @Autowired
    private UserService userService;

    @GetMapping(value="all")
    public ResponseEntity<List<UserViewModel>> getUsers() {
        List<UserViewModel> result = new ArrayList<>();
        result = userService.findAll().stream()
                .map(user->userService.convertToUserViewModel(user))
                .collect(Collectors.toList());
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }    
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}