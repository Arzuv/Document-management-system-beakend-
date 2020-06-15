package tm.arzuv.app.dto;

import java.util.Date;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tm.arzuv.app.model.Roles;
import tm.arzuv.app.model.User;

@Getter @Setter @NoArgsConstructor
public class UserViewModel {
    private Integer id;
	private String email;
	private String password;
    private String firstname;
    private String lastname;
    private Date created;
    private List<Roles> roles;

    public User toUser() {
        User user = new User();
        if (id != null)
            user.setId(id);
        if (password != null)
            user.setPassword(password);
        user.setEmail(email);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setRoles(roles);
        return user;
    }

    public static UserViewModel fromUser(User user) {
        UserViewModel userViewModel = new UserViewModel();
        userViewModel.setId(user.getId());
        userViewModel.setEmail(user.getEmail());
        userViewModel.setFirstname(user.getFirstname());
        userViewModel.setLastname(user.getLastname());
        userViewModel.setCreated(user.getCreated());
        userViewModel.setRoles(user.getRoles());
        return userViewModel;
    }

    public void updatePassword() {
        this.password = new BCryptPasswordEncoder().encode(this.password);
    }
}
