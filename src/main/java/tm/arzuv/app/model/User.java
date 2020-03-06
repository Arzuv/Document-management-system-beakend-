package tm.arzuv.app.model;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "users")
@Getter @Setter @NoArgsConstructor
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

    @Override
    public String toString() {
        return "User [" + "created=" + this.getCreated() + ", id=" + this.getId() 
        + ", status=" + this.getStatus() + ", updated=" + this.getUpdated()
         + ", active=" + active + ", email=" + email + ", firstname=" + 
         firstname + ", lastname=" + lastname
                + ", password=" + password + ", roles=" + roles + "]";
    }
    
    
}