package tm.arzuv.app.Service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import tm.arzuv.app.dto.UserViewModel;
import tm.arzuv.app.model.Status;
import tm.arzuv.app.model.User;

public interface UserService {
    public ResponseEntity<Page<UserViewModel>> findAll(Integer integer, String sortBy);
    public User findByEmail(String email);
}
