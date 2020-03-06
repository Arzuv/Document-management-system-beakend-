package tm.arzuv.app.model;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority{
	ADMIN, USER;

	Roles(){}

	@Override
	public String getAuthority() {
		return name();
	}
}