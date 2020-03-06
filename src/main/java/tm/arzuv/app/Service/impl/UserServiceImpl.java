package tm.arzuv.app.Service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tm.arzuv.app.Service.UserService;
import tm.arzuv.app.dao.UserViewModel;
import tm.arzuv.app.model.User;
import tm.arzuv.app.repository.UserRepository;

@Component
@Service
public final class UserServiceImpl implements UserService {
    @Autowired
	private UserRepository userRepository;
	
	@Override
	public ResponseEntity<List<UserViewModel>> findAll() {
		List<UserViewModel> result = new ArrayList<>();
        List<User> users = userRepository.findAll();
        if (users.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            
        result = users.stream()
                .map(user->convertToUserViewModel(user))
                .collect(Collectors.toList());

        return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<UserViewModel>> findAllAndSort(String condition) {
		List<UserViewModel> result = new ArrayList<>();
        List<User> users = userRepository.findAll(Sort.by(condition));
        if (users.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        result = users.stream()
            .map(user->convertToUserViewModel(user))
            .collect(Collectors.toList());

        return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Override
	public User save(User u) {
		return userRepository.save(u);
	}

	@Override
	public ResponseEntity<UserViewModel> findById(String id) {
		UserViewModel result=null;
        try {
            User user = userRepository.findById(Integer.parseInt(id));
            if (user == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            result = convertToUserViewModel(user);   
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserViewModel> findByEmail(String email) {
		User user = userRepository.findByEmail(email);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        UserViewModel result = convertToUserViewModel(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> delete(String id) {
		//Use findById() because when use existsById error: 
		// error starting ApplicationContext. To display the conditions report re-run your application with 'debug' enabled.
        try {
			if (userRepository.findById(Integer.parseInt(id))==null)
				return new ResponseEntity<>("false", HttpStatus.NOT_FOUND);
            userRepository.deleteById(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("true", HttpStatus.OK);
	}

	@Override
	public UserViewModel convertToUserViewModel(User u) {
		UserViewModel uvm = new UserViewModel();
        uvm.setId(u.getId());
        uvm.setEmail(u.getEmail());
        uvm.setFirstname(u.getFirstname());
		uvm.setLastname(u.getLastname());
		uvm.setCreated(u.getCreated());
		uvm.setRoles(u.getRoles());
        return uvm;
	}

	@Override
	public User convertToUser(UserViewModel u) {
		User user = new User();
        if (u.getId() != 0)
			user = userRepository.findById(u.getId());
		if (u.getPassword() != null && !u.getPassword().isEmpty()) {
			u.updatePassword();	
			user.setPassword(u.getPassword());
		}
        user.setEmail(u.getEmail());
        user.setFirstname(u.getFirstname());
		user.setLastname(u.getLastname());
		user.setRoles(u.getRoles());
        return user;
	}
}