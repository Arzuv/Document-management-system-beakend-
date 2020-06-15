package tm.arzuv.app.security.jwt;

import tm.arzuv.app.model.Status;
import tm.arzuv.app.model.User;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getPassword(),
                user.getEmail(),
                user.getStatus().equals(Status.ACTIVE),
                user.getUpdated(),
                user.getRoles()
        );
    }

}
