package tm.arzuv.app.Service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tm.arzuv.app.Service.UserService;
import tm.arzuv.app.dto.UserViewModel;
import tm.arzuv.app.model.Status;
import tm.arzuv.app.model.User;
import tm.arzuv.app.repository.UserRepository;

@Component
@Service
@Slf4j
public final class UserServiceImpl implements UserService {
	private UserRepository userRepository;
	private Integer COUNT_PAGE=5;

    @Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public ResponseEntity<Page<UserViewModel>> findAll(Integer page, String sortBy) {
		Page<UserViewModel> result;
        Page<User> users = userRepository.findAll(PageRequest.of(page, COUNT_PAGE, Sort.Direction.ASC, sortBy));
        if (!users.hasContent())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        result = users.map(user->UserViewModel.fromUser(user));

        return new ResponseEntity(result, HttpStatus.OK);
	}

	@Override
	public User findByEmail(String email) {
		User result = userRepository.findByEmailAndStatus(email, Status.ACTIVE);
		log.info("IN findByEmail - user: {} found by email: {}", result, result.getEmail());
		return result;
	}
}
