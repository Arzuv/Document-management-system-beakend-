package tm.arzuv.app.dao;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tm.arzuv.app.model.Roles;

@Getter @Setter @NoArgsConstructor
public class UserViewModel {
    private int id;
	private String email;
	private String password;
    private String firstname;
    private String lastname;
    private Date created;
    private List<Roles> roles;

    public void updatePassword() {
        this.password = new BCryptPasswordEncoder().encode(this.password);
    }

    @Override
    public String toString() {
        return "UserViewModel [created=" + created + ", email=" + email + ", firstname=" + firstname + ", id=" + id
                + ", lastname=" + lastname + ", password=" + password + ", roles=" + Arrays.toString(roles.toArray()) + "]";
    }
}