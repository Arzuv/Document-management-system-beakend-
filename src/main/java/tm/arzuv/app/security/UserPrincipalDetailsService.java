package tm.arzuv.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tm.arzuv.app.Service.UserService;
import tm.arzuv.app.model.Roles;
import tm.arzuv.app.model.User;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new UserPrincipal(userService.findByEmail(email));
    }

}
