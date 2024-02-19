package org.sergiorebelo.piupiu.usermanagement.api.dto;
import org.sergiorebelo.piupiu.usermanagement.entity.User;

import java.util.Objects;

//import javax.persistence.*;



public class UserDTO {
    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    private final Long id;

    private String username;

    private String password;

    // create a DTO from the Entity
    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = "*****";
    }

    // Additional fields such as email, full name, etc.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO user = (UserDTO) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

    public UserDTO(String username, String password) {
        this.id= 0L;
        this.username = username;
        this.password = password;
    }

    //empty constructor
    public UserDTO() {
        this.id=0L;
        //for test purposes only
    }
    // Getters and setters

    //ID

    public Long getId() { return id; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //Password

    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }

}