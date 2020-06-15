package tm.arzuv.app.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tm.arzuv.app.model.User;
import tm.arzuv.app.security.jwt.JwtUser;
import tm.arzuv.app.security.jwt.JwtUserFactory;
import tm.arzuv.app.Service.UserService;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = userService.findByEmail(email);
        if (u == null) {
            throw new UsernameNotFoundException("User with email " + email + " not found!");
        }
        JwtUser jwtUser = JwtUserFactory.create(u);
        log.info("IN loadByUsername - user with email: {} successfully loaded", email);
        return jwtUser;
    }
}
