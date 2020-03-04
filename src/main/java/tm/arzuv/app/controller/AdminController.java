package tm.arzuv.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import tm.arzuv.app.Service.UserService;
import tm.arzuv.app.controller.ViewModel.UserAuthViewModel;
import tm.arzuv.app.controller.ViewModel.UserViewModel;
import tm.arzuv.app.model.User;
import tm.arzuv.app.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/admin/")
public class AdminController {

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