package tm.arzuv.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import tm.arzuv.app.Service.UserService;
import tm.arzuv.app.dao.UserViewModel;
import tm.arzuv.app.model.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin
@RequestMapping("/admin/")
public class AdminRestController {
    @Autowired
    private UserService userService;

    @GetMapping(value="/user/all")
    public ResponseEntity<List<UserViewModel>> getUsers() {
        return userService.findAll();
    }

    @GetMapping(value="/user/all/{condition}")
    public ResponseEntity<List<UserViewModel>> getUsersAndSort(@PathVariable String condition) {
        return userService.findAllAndSort(condition);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<UserViewModel> getUserById(@PathVariable String id) {
        return userService.findById(id);
    }

    @GetMapping(value = "/user/email/{email}")
    public ResponseEntity<UserViewModel> getUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @PostMapping(value="/user/create")
    public ResponseEntity<UserViewModel> createUser(@RequestBody UserViewModel user) {
        UserViewModel result = null;
        result = userService.convertToUserViewModel(
                        userService.save(userService.convertToUser(user))
                    );

        if (result == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping(value="/user/update")
    public ResponseEntity<UserViewModel> updateUser(@RequestBody UserViewModel user) {
        UserViewModel result = null;
        User u = userService.convertToUser(user);
        System.out.println(u.toString());
        userService.save(u);
        // result = userService.convertToUserViewModel();
        if (result == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value="/user/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        return userService.delete(id);
    }
}