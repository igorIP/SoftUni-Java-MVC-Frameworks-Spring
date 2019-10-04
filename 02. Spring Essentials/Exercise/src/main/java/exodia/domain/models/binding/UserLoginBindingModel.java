package exodia.domain.models.binding;

import exodia.domain.api.Bindable;
import exodia.domain.entities.User;

public class UserLoginBindingModel implements Bindable<User> {
    private String username;
    private String password;

    public UserLoginBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
