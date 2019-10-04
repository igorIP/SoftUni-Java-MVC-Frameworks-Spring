package exodia.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends BaseUuidEntity{

    @Column(unique = true, nullable = false, length = 32)
    private String username;

    @Column(nullable = false, length = 75)
    private String password;

    @Column(unique = true, nullable = false, length = 64)
    private String email;

    public User() {
    }

    public String getPassword() {
        return password;
    }
}
