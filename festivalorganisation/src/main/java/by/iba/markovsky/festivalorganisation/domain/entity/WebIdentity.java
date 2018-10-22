package by.iba.markovsky.festivalorganisation.domain.entity;

import java.io.Serializable;
import java.util.Objects;

public class WebIdentity implements Serializable {

    private static final long serialVersionUID = 3276480509050536113L;

    private int id;

    private String username;
    private String password;
    private String email;
    private String telephone;

    public WebIdentity() {
    }
    public WebIdentity(String username, String password, String email, String telephone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
    }
    public WebIdentity(WebIdentity webIdentity) {
        this.username = webIdentity.username;
        this.password = webIdentity.password;
        this.email = webIdentity.email;
        this.telephone = webIdentity.telephone;
    }

    //Setters
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    //Getters
    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getTelephone() {
        return telephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebIdentity that = (WebIdentity) o;
        return id == that.id &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(email, that.email) &&
                Objects.equals(telephone, that.telephone);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, telephone);
    }
    @Override
    public String toString() {
        return "WebIdentity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }

}
