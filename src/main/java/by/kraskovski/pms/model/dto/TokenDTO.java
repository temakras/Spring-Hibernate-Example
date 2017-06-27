package by.kraskovski.pms.model.dto;

import by.kraskovski.pms.model.User;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Token Data Transfer Object class
 */
public class TokenDTO implements Serializable {

    /**
     * {@link User}'s token
     */
    private String token;

    /**
     * Current logged {@link User}
     */
    private User user;

    public TokenDTO() {
    }

    public TokenDTO(final String token, final User user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public void setUser(final User user) {
        user.setPassword(StringUtils.EMPTY);
        this.user = user;
    }
}
