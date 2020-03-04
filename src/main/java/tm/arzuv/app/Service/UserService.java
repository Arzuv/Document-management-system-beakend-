package tm.arzuv.app.Service;

import java.util.List;
import tm.arzuv.app.controller.ViewModel.UserViewModel;
import tm.arzuv.app.model.User;

public interface UserService {
    List<User> findAll(); 
    User findById(int id);
    User findByEmail(String email);
    void delete(int id); 
    public UserViewModel convertToUserViewModel(User u);
    public User convertToUser(UserViewModel u);
}