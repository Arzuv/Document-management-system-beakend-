package tm.arzuv.app.Service;

import java.util.List;
import tm.arzuv.app.dao.UserViewModel;
import tm.arzuv.app.model.User;

public interface UserService {
    public List<User> findAll(); 
    public List<User> findAllAndSort(String condition);
    public User findById(int id);
    public User findByEmail(String email);
    public User save(User u);
    public boolean delete(int id); 
    public UserViewModel convertToUserViewModel(User u);
    public User convertToUser(UserViewModel u);
}