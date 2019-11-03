package ru.stqa.pft.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "mantis_user_table")
public class UserData {

    @Id
    @Column(name = "id")
    public Integer id;

    @Column(name = "username")
    public String username;

    @Column(name = "email")
    public String email;

    @Column(name = "password")
    public String password;

    public Integer getId() { return id; }

    public String getName() { return username; }

    public String getEmail() { return email; }

    public String getPassword() { return password; }

    public UserData withId(Integer id) {
        this.id = id;
        return this;
    }

    public UserData withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserData withEmail(String email) {
        this.username = email;
        return this;
    }

    public UserData withPassword(String password) {
        this.username = password;
        return this;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return Objects.equals(id, userData.id) &&
                Objects.equals(username, userData.username) &&
                Objects.equals(email, userData.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email);
    }
}
