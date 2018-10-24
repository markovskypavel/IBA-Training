package by.iba.markovsky.festivalorganisation.model.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class WebIdentity implements Serializable {

    private static final long serialVersionUID = 3276480509050536113L;

    private int id;

    private String username;
    private String password;
    private String email;
    private String telephone;
    private boolean status; //Admin or Simple user

    private Identity identity;
    private List<Activity> activities;

    public WebIdentity() {
    }
    public WebIdentity(int id, String username, String password, String email, String telephone, boolean status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
        this.status = status;
    }
    public WebIdentity(int id, String username, String password, String email, String telephone, boolean status, Identity identity, List<Activity> activities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
        this.status = status;
        this.identity = identity;
        this.activities = activities;
    }
    public WebIdentity(int id, String username, String password, String email, String telephone, boolean status, Identity identity) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
        this.status = status;
        this.identity = identity;
    }
    public WebIdentity(int id, int idIdentity, String username, String password, String email, String telephone, boolean status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
        this.status = status;
        this.identity = new Identity(idIdentity);
    }
    public WebIdentity(String username, String password, String email, String telephone, boolean status) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
        this.status = status;
    }
    public WebIdentity(String username, String password, String email, String telephone, boolean status, Identity identity) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
        this.status = status;
        this.identity = identity;
    }
    public WebIdentity(String username, String password, String email, String telephone, boolean status, Identity identity, List<Activity> activities) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
        this.status = status;
        this.identity = identity;
        this.activities = activities;
    }
    public WebIdentity(WebIdentity webIdentity) {
        this.username = webIdentity.username;
        this.password = webIdentity.password;
        this.email = webIdentity.email;
        this.telephone = webIdentity.telephone;
        this.status = webIdentity.status;
        this.identity = webIdentity.identity;
        this.activities = webIdentity.activities;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }
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
    public void setStatus(boolean status) {
        this.status = status;
    }
    public void setIdentity(Identity identity) {
        this.identity = identity;
    }
    public void setActivities(List<Activity> activities) {
        this.activities = activities;
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
    public boolean isStatus() {
        return status;
    }
    public Identity getIdentity() {
        return identity;
    }
    public List<Activity> getActivities() {
        return activities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebIdentity that = (WebIdentity) o;
        return id == that.id &&
                status == that.status &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(email, that.email) &&
                Objects.equals(telephone, that.telephone) &&
                Objects.equals(identity, that.identity) &&
                Objects.equals(activities, that.activities);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, telephone, status, identity, activities);
    }
    @Override
    public String toString() {
        return "WebIdentity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", status=" + status +
                ", identity=" + identity +
                ", activities=" + activities +
                '}';
    }

}
