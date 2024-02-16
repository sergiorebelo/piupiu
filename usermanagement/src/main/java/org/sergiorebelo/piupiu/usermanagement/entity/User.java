package org.sergiorebelo.piupiu.usermanagement.entity;
import jakarta.persistence.*;

import java.util.Objects;

//import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String hashedPassword;

    @Column(nullable = false)
    private String salt;

    // Additional fields such as email, full name, etc.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

    public User(String username, String password) {
        this.username = username;
        this.hashedPassword = password;
    }

    //emptu constructor
    public User() {
        //for test purposes only
    }
    // Getters and setters

    //ID



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //Password

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}