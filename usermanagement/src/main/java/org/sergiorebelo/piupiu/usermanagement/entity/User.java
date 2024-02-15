package org.sergiorebelo.piupiu.usermanagement.entity;
import jakarta.persistence.*;

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
    private String password;

    // Additional fields such as email, full name, etc.

    // Getters and setters

    //ID

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //Username

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //Password

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}