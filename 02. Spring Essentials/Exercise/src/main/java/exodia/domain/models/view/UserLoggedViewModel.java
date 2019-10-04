package exodia.domain.models.view;

import exodia.domain.api.Bindable;
import exodia.domain.entities.User;

public class UserLoggedViewModel implements Bindable<User> {

    private String id;
    private String username;

    public UserLoggedViewModel() {
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
