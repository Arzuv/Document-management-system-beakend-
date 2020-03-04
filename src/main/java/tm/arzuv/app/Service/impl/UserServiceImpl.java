package tm.arzuv.app.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tm.arzuv.app.Service.UserService;
import tm.arzuv.app.controller.ViewModel.UserViewModel;
import tm.arzuv.app.model.User;
import tm.arzuv.app.repository.UserRepository;

@Component
@Service
public class UserServiceImpl implements UserService {
    @Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(int id) {
		return userRepository.findById(id);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void delete(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public UserViewModel convertToUserViewModel(User u) {
		UserViewModel uvm = new UserViewModel();
        uvm.setId(u.getId());
        uvm.setEmail(u.getEmail());
        uvm.setFirstname(u.getFirstname());
        uvm.setLastname(u.getLastname());
        return uvm;
	}

	@Override
	public User convertToUser(UserViewModel u) {
		User user = new User();
        if (u.getId() != 0)
            user.setId(u.getId());
        user.setEmail(u.getEmail());
        user.setFirstname(u.getFirstname());
        user.setLastname(u.getLastname());
        return user;
	}
}