package tm.arzuv.app.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import tm.arzuv.app.dao.UserViewModel;
import tm.arzuv.app.model.User;

public interface UserService {
    public ResponseEntity<List<UserViewModel>> findAll();
    public ResponseEntity<List<UserViewModel>> findAllAndSort(String condition);
    public ResponseEntity<UserViewModel> findById(String id);
    public ResponseEntity<UserViewModel> findByEmail(String email);
    public User save(User u);
    public ResponseEntity<String> delete(String id);
    public UserViewModel convertToUserViewModel(User u);
    public User convertToUser(UserViewModel u);
}