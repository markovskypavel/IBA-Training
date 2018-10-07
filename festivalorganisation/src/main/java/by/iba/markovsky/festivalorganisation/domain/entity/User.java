package by.iba.markovsky.festivalorganisation.domain.entity;

import java.util.Objects;

public class User extends Human {

    private static final long serialVersionUID = -8032417545535246095L;

    private String username;
    private String password;
    private String email;
    private String telephone;
    private boolean status; //Admin or Simple user

    public User() {
        super();
    }
    public User(String username, String password, String email, String telephone, boolean status) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
        this.status = status;
    }
    public User(String name, String surname, int age, String username, String password, String email, String telephone, boolean status) {
        super(name, surname, age);
        this.username = username;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
        this.status = status;
    }
    public User(String name, String surname, int age) {
        super(name, surname, age);
    }
    public User(User user) {
        this.name = user.name;
        this.surname = user.surname;
        this.age = user.age;
        this.username = user.username;
        this.password = user.password;
        this.email = user.email;
        this.telephone = user.telephone;
        this.status = user.status;
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
    public void setStatus(boolean status) {
        this.status = status;
    }

    //Getters
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return status == user.status &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(telephone, user.telephone);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password, email, telephone, status);
    }
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }

}
