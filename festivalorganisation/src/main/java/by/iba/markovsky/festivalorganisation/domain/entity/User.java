package by.iba.markovsky.festivalorganisation.domain.entity;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private static final long serialVersionUID = -8032417545535246095L;

    private int id;

    private Activity activity;

    private WebIdentity webIdentity;
    private Identity identity;
    private boolean status; //Admin or Simple user

    public User() {
    }
    public User(String name, String surname, int age, String username, String password, String email, String telephone, boolean status) {
        this.webIdentity = new WebIdentity(username, password, email, telephone);
        this.identity = new Identity(name, surname, age);
        this.status = status;
    }
    public User(WebIdentity webIdentity, Identity identity, boolean status) {
        this.webIdentity = webIdentity;
        this.identity = identity;
        this.status = status;
    }
    public User(User user) {
        this.webIdentity = user.webIdentity;
        this.identity = user.identity;
        this.status = user.status;
    }

    //Setters
    public void setActivity(Activity activity) {
        this.activity = activity;
    }
    public void setWebIdentity(WebIdentity webIdentity) {
        this.webIdentity = webIdentity;
    }
    public void setIdentity(Identity identity) {
        this.identity = identity;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    //Getters
    public int getId() {
        return id;
    }
    public Activity getActivity() {
        return activity;
    }
    public WebIdentity getWebIdentity() {
        return webIdentity;
    }
    public Identity getIdentity() {
        return identity;
    }
    public boolean isStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                status == user.status &&
                Objects.equals(webIdentity, user.webIdentity) &&
                Objects.equals(identity, user.identity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, webIdentity, identity, status);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", webIdentity=" + webIdentity +
                ", identity=" + identity +
                ", status=" + status +
                '}';
    }
}
