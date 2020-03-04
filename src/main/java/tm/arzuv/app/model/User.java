package tm.arzuv.app.model;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import java.util.List;

@Entity(name = "users")
public class User extends BaseEntity {
 
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable=false)
    private String password;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
	private String lastname;
	
	private boolean active;
	
	@ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
	@CollectionTable(name="roles", joinColumns = @JoinColumn(name="user_id"))
	@Enumerated(EnumType.STRING)
	private List<Roles> roles;

    public User() {

	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}